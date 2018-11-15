package com.meitaomart.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taxjar.exception.TaxjarException;
import com.taxjar.model.categories.CategoryResponse;
import com.taxjar.model.customers.CustomerResponse;
import com.taxjar.model.customers.CustomersResponse;
import com.taxjar.model.nexus.RegionResponse;
import com.taxjar.model.rates.RateResponse;
import com.taxjar.model.summarized_rates.SummaryRateResponse;
import com.taxjar.model.taxes.TaxResponse;
import com.taxjar.model.transactions.OrderResponse;
import com.taxjar.model.transactions.OrdersResponse;
import com.taxjar.model.transactions.RefundResponse;
import com.taxjar.model.transactions.RefundsResponse;
import com.taxjar.model.validations.ValidationResponse;
import com.taxjar.net.Endpoints;
import com.taxjar.net.Listener;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Map;

public class Taxjar {
    public static final String DEFAULT_API_URL = "https://api.taxjar.com";
    public static final String SANDBOX_API_URL = "https://api.sandbox.taxjar.com";
    public static final String API_VERSION = "v2";
    public static final String VERSION = "1.2.0";
    protected static Endpoints apiService;
    protected static String apiUrl;
    protected static String apiToken;

    //set up API token key
    public Taxjar(final String apiToken) {
        this.apiToken = apiToken;
        this.apiUrl = DEFAULT_API_URL;
        buildClient(null);
    }

    public Taxjar(final String apiToken, Map<String, String> params) {
        this.apiToken = apiToken;
        this.apiUrl = DEFAULT_API_URL;
        buildClient(params);
    }

    //build up taxjar http client
    public void buildClient(Map<String, String> params) {
        final String apiToken = this.apiToken;

        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (param.getKey() == "apiUrl") {
                    this.apiUrl = param.getValue();
                }
            }
        }

        // http interceptor protection
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + apiToken)
                        .addHeader("User-Agent", "TaxJarJava/" + VERSION)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.apiUrl + "/" + API_VERSION + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        apiService = retrofit.create(Endpoints.class);
    }

    //get api config
    public String getApiConfig(String key) {
        try {
            return getClass().getDeclaredField(key).get(this).toString();
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            return "No such field or illegal access";
        }
    }

    // set api config
    public void setApiConfig(String key, String value) {
        try {
            getClass().getDeclaredField(key).set(this, value);
            buildClient(null);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
        	
        }
    }

    public CategoryResponse categories() throws TaxjarException {
        Call<CategoryResponse> call = apiService.getCategories();

        try {
            Response<CategoryResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void categories(final Listener<CategoryResponse> listener) {
        Call<CategoryResponse> call = apiService.getCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RateResponse ratesForLocation(String zip) throws TaxjarException {
        Call<RateResponse> call = apiService.getRate(zip);

        try {
            Response<RateResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public RateResponse ratesForLocation(String zip, Map<String, String> params) throws TaxjarException {
        Call<RateResponse> call = apiService.getRate(zip, params);

        try {
            Response<RateResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void ratesForLocation(String zip, final Listener<RateResponse> listener) throws TaxjarException {
        Call<RateResponse> call = apiService.getRate(zip);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void ratesForLocation(String zip, Map<String, String> params, final Listener<RateResponse> listener) throws TaxjarException {
        Call<RateResponse> call = apiService.getRate(zip, params);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public TaxResponse taxForOrder(Map<String, Object> params) throws TaxjarException {
        Call<TaxResponse> call = apiService.getTax(params);

        try {
            Response<TaxResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void taxForOrder(Map<String, Object> params, final Listener<TaxResponse> listener) {
        Call<TaxResponse> call = apiService.getTax(params);

        call.enqueue(new Callback<TaxResponse>() {
            @Override
            public void onResponse(Call<TaxResponse> call, Response<TaxResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<TaxResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public OrdersResponse listOrders() throws TaxjarException {
        Call<OrdersResponse> call = apiService.getOrders();

        try {
            Response<OrdersResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public OrdersResponse listOrders(Map<String, String> params) throws TaxjarException {
        Call<OrdersResponse> call = apiService.getOrders(params);

        try {
            Response<OrdersResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void listOrders(final Listener<OrdersResponse> listener) {
        Call<OrdersResponse> call = apiService.getOrders();

        call.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void listOrders(Map<String, String> params, final Listener<OrdersResponse> listener) {
        Call<OrdersResponse> call = apiService.getOrders(params);

        call.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public OrderResponse showOrder(String transactionId) throws TaxjarException {
        Call<OrderResponse> call = apiService.getOrder(transactionId);

        try {
            Response<OrderResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void showOrder(String transactionId, final Listener<OrderResponse> listener) {
        Call<OrderResponse> call = apiService.getOrder(transactionId);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public OrderResponse createOrder(Map<String, Object> params) throws TaxjarException {
        Call<OrderResponse> call = apiService.createOrder(params);

        try {
            Response<OrderResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void createOrder(Map<String, Object> params, final Listener<OrderResponse> listener) {
        Call<OrderResponse> call = apiService.createOrder(params);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public OrderResponse updateOrder(String transactionId, Map<String, Object> params) throws TaxjarException {
        Call<OrderResponse> call = apiService.updateOrder(transactionId, params);

        try {
            Response<OrderResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void updateOrder(String transactionId, Map<String, Object> params, final Listener<OrderResponse> listener) {
        Call<OrderResponse> call = apiService.updateOrder(transactionId, params);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public OrderResponse deleteOrder(String transactionId) throws TaxjarException {
        Call<OrderResponse> call = apiService.deleteOrder(transactionId);

        try {
            Response<OrderResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void deleteOrder(String transactionId, final Listener<OrderResponse> listener) {
        Call<OrderResponse> call = apiService.deleteOrder(transactionId);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundsResponse listRefunds() throws TaxjarException {
        Call<RefundsResponse> call = apiService.getRefunds();

        try {
            Response<RefundsResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public RefundsResponse listRefunds(Map<String, String> params) throws TaxjarException {
        Call<RefundsResponse> call = apiService.getRefunds(params);

        try {
            Response<RefundsResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void listRefunds(final Listener<RefundsResponse> listener) {
        Call<RefundsResponse> call = apiService.getRefunds();

        call.enqueue(new Callback<RefundsResponse>() {
            @Override
            public void onResponse(Call<RefundsResponse> call, Response<RefundsResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void listRefunds(Map<String, String> params, final Listener<RefundsResponse> listener) {
        Call<RefundsResponse> call = apiService.getRefunds(params);

        call.enqueue(new Callback<RefundsResponse>() {
            @Override
            public void onResponse(Call<RefundsResponse> call, Response<RefundsResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundResponse showRefund(String transactionId) throws TaxjarException {
        Call<RefundResponse> call = apiService.getRefund(transactionId);

        try {
            Response<RefundResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void showRefund(String transactionId, final Listener<RefundResponse> listener) {
        Call<RefundResponse> call = apiService.getRefund(transactionId);

        call.enqueue(new Callback<RefundResponse>() {
            @Override
            public void onResponse(Call<RefundResponse> call, Response<RefundResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundResponse createRefund(Map<String, Object> params) throws TaxjarException {
        Call<RefundResponse> call = apiService.createRefund(params);

        try {
            Response<RefundResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void createRefund(Map<String, Object> params, final Listener<RefundResponse> listener) {
        Call<RefundResponse> call = apiService.createRefund(params);

        call.enqueue(new Callback<RefundResponse>() {
            @Override
            public void onResponse(Call<RefundResponse> call, Response<RefundResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundResponse createRefund(String transactionId, Map<String, Object> params) throws TaxjarException {
        Call<RefundResponse> call = apiService.updateRefund(transactionId, params);

        try {
            Response<RefundResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void createRefund(String transactionId, Map<String, Object> params, final Listener<RefundResponse> listener) {
        Call<RefundResponse> call = apiService.updateRefund(transactionId, params);

        call.enqueue(new Callback<RefundResponse>() {
            @Override
            public void onResponse(Call<RefundResponse> call, Response<RefundResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundResponse updateRefund(String transactionId, Map<String, Object> params) throws TaxjarException {
        Call<RefundResponse> call = apiService.updateRefund(transactionId, params);

        try {
            Response<RefundResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void updateRefund(String transactionId, Map<String, Object> params, final Listener<RefundResponse> listener) {
        Call<RefundResponse> call = apiService.updateRefund(transactionId, params);

        call.enqueue(new Callback<RefundResponse>() {
            @Override
            public void onResponse(Call<RefundResponse> call, Response<RefundResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RefundResponse deleteRefund(String transactionId) throws TaxjarException {
        Call<RefundResponse> call = apiService.deleteRefund(transactionId);

        try {
            Response<RefundResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void deleteRefund(String transactionId, final Listener<RefundResponse> listener) {
        Call<RefundResponse> call = apiService.deleteRefund(transactionId);

        call.enqueue(new Callback<RefundResponse>() {
            @Override
            public void onResponse(Call<RefundResponse> call, Response<RefundResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RefundResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public CustomersResponse listCustomers() throws TaxjarException {
        Call<CustomersResponse> call = apiService.getCustomers();

        try {
            Response<CustomersResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public CustomersResponse listCustomers(Map<String, String> params) throws TaxjarException {
        Call<CustomersResponse> call = apiService.getCustomers(params);

        try {
            Response<CustomersResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void listCustomers(final Listener<CustomersResponse> listener) {
        Call<CustomersResponse> call = apiService.getCustomers();

        call.enqueue(new Callback<CustomersResponse>() {
            @Override
            public void onResponse(Call<CustomersResponse> call, Response<CustomersResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomersResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void listCustomers(Map<String, String> params, final Listener<CustomersResponse> listener) {
        Call<CustomersResponse> call = apiService.getCustomers(params);

        call.enqueue(new Callback<CustomersResponse>() {
            @Override
            public void onResponse(Call<CustomersResponse> call, Response<CustomersResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomersResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public CustomerResponse showCustomer(String customerId) throws TaxjarException {
        Call<CustomerResponse> call = apiService.getCustomer(customerId);

        try {
            Response<CustomerResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void showCustomer(String customerId, final Listener<CustomerResponse> listener) {
        Call<CustomerResponse> call = apiService.getCustomer(customerId);

        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public CustomerResponse createCustomer(Map<String, Object> params) throws TaxjarException {
        Call<CustomerResponse> call = apiService.createCustomer(params);

        try {
            Response<CustomerResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void createCustomer(Map<String, Object> params, final Listener<CustomerResponse> listener) {
        Call<CustomerResponse> call = apiService.createCustomer(params);

        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public CustomerResponse updateCustomer(String customerId, Map<String, Object> params) throws TaxjarException {
        Call<CustomerResponse> call = apiService.updateCustomer(customerId, params);

        try {
            Response<CustomerResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void updateCustomer(String customerId, Map<String, Object> params, final Listener<CustomerResponse> listener) {
        Call<CustomerResponse> call = apiService.updateCustomer(customerId, params);

        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public CustomerResponse deleteCustomer(String customerId) throws TaxjarException {
        Call<CustomerResponse> call = apiService.deleteCustomer(customerId);

        try {
            Response<CustomerResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void deleteCustomer(String customerId, final Listener<CustomerResponse> listener) {
        Call<CustomerResponse> call = apiService.deleteCustomer(customerId);

        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public RegionResponse nexusRegions() throws TaxjarException {
        Call<RegionResponse> call = apiService.getRegions();

        try {
            Response<RegionResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void nexusRegions(final Listener<RegionResponse> listener) {
        Call<RegionResponse> call = apiService.getRegions();

        call.enqueue(new Callback<RegionResponse>() {
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public ValidationResponse validate(Map<String, String> params) throws TaxjarException {
        Call<ValidationResponse> call = apiService.getValidation(params);

        try {
            Response<ValidationResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void validate(Map<String, String> params, final Listener<ValidationResponse> listener) {
        Call<ValidationResponse> call = apiService.getValidation(params);

        call.enqueue(new Callback<ValidationResponse>() {
            @Override
            public void onResponse(Call<ValidationResponse> call, Response<ValidationResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ValidationResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public SummaryRateResponse summaryRates() throws TaxjarException {
        Call<SummaryRateResponse> call = apiService.getSummaryRates();

        try {
            Response<SummaryRateResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new TaxjarException(response.errorBody().string());
            }
        } catch (IOException e) {
            EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
        }

        return null;
    }

    public void summaryRates(final Listener<SummaryRateResponse> listener) {
        Call<SummaryRateResponse> call = apiService.getSummaryRates();

        call.enqueue(new Callback<SummaryRateResponse>() {
            @Override
            public void onResponse(Call<SummaryRateResponse> call, Response<SummaryRateResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        TaxjarException exception = new TaxjarException(response.errorBody().string());
                        listener.onError(exception);
                    } catch (IOException e) {
                        EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SummaryRateResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
