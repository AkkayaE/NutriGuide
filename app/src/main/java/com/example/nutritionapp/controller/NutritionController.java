package com.example.nutritionapp.controller;

import com.example.nutritionapp.model.FoodItem;
import com.example.nutritionapp.network.MalformedNutritionDataException;
import com.example.nutritionapp.network.NutritionApiClient;
import com.example.nutritionapp.util.SearchValidator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

public class NutritionController {
    private final NutritionApiClient apiClient;
    private final SearchValidator validator;
    private final Executor backgroundExecutor;
    private final Executor callbackExecutor;

    public NutritionController(
            NutritionApiClient apiClient,
            SearchValidator validator,
            Executor backgroundExecutor,
            Executor callbackExecutor
    ) {
        this.apiClient = apiClient;
        this.validator = validator;
        this.backgroundExecutor = backgroundExecutor;
        this.callbackExecutor = callbackExecutor;
    }

    public void search(String query, SearchCallback callback) {
        SearchValidator.ValidationResult validationResult = validator.validate(query);
        if (!validationResult.isValid()) {
            callbackExecutor.execute(() -> callback.onValidationError(validationResult.getError()));
            return;
        }

        callback.onLoading();
        backgroundExecutor.execute(() -> {
            try {
                List<FoodItem> results = apiClient.searchFoods(validationResult.getNormalizedQuery());
                callbackExecutor.execute(() -> {
                    if (results.isEmpty()) {
                        callback.onEmptyResults();
                    } else {
                        callback.onSuccess(results);
                    }
                });
            } catch (MalformedNutritionDataException exception) {
                callbackExecutor.execute(() -> callback.onError(SearchError.API));
            } catch (IOException exception) {
                callbackExecutor.execute(() -> callback.onError(SearchError.NETWORK));
            }
        });
    }

    public interface SearchCallback {
        void onLoading();

        void onSuccess(List<FoodItem> foodItems);

        void onEmptyResults();

        void onValidationError(SearchValidator.ValidationError error);

        void onError(SearchError error);
    }

    public enum SearchError {
        NETWORK,
        API
    }
}
