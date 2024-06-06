package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.Pedido;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MercadoPagoController {
    public PreferenceMP getPreferenciaIdMercadoPago(Pedido pedido){
        try {
            MercadoPagoConfig.setAccessToken("TEST-8938214283872723-052519-92ecd57450cc2945ff42176ba32d1a39-400595763");
            List<PreferenceItemRequest> items = new ArrayList<>();


            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title("Tienda de instrumentos")
                    .currencyId("ARG")
                    .pictureUrl("https://www.mercadopago.com/org-img/MP3/home/logomp3.gif")
                    .description("Descrição do Item")
                    .categoryId("art")
                    .quantity(1)
                    .unitPrice(new BigDecimal(pedido.getTotal().toString()))
                    .build();


            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/mpsuccess")
                    .pending("http://localhost:5173/mppending").failure("http://localhost:5173/mpfailure").build();

            List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("argencard").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("cmr").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("cencosud").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("cordobesa").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("diners").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("tarshop").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("debcabal").build());
            excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("maestro").build());

            List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
            excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());


            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();



            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}