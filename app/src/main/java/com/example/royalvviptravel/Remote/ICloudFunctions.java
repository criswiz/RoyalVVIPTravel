package com.example.royalvviptravel.Remote;

import com.example.royalvviptravel.Model.BraintreeToken;
import com.example.royalvviptravel.Model.BraintreeTransaction;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface ICloudFunctions {
    @GET ("token")
    Observable<BraintreeToken> getToken();

    @POST ("checkout")
    Observable<BraintreeTransaction> submitPayment(@Field("amount") double amount,
                                                   @Field("payment_method_nonce") String nonce);
}
