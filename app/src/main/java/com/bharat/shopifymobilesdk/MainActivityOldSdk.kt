package com.bharat.shopifymobilesdk

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.shopify.buy3.*
import com.shopify.buy3.Storefront.*
import com.shopify.graphql.support.ID
import com.shopify.graphql.support.Input
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference


class MainActivityOldSdk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*  webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(
                view: WebView?,
                url: String?,
                favicon: Bitmap?
            ) {
                // do your stuff here
            }

            override fun onPageFinished(view: WebView?, url: String?) {

            }

        })
         initShopify()

   btn_cheout.setOnClickListener {
       val input = CheckoutCreateInput()
           .setLineItemsInput(
               Input.value(
                   Arrays.asList(

                       CheckoutLineItemInput(
                           5,
                           ID("<product id>")
                       )

                       )
               )
           )
       val query = mutation { mutationQuery: MutationQuery ->
           mutationQuery
               .checkoutCreate(
                   input
               ) { createPayloadQuery: CheckoutCreatePayloadQuery ->
                   createPayloadQuery
                       .checkout { checkoutQuery: CheckoutQuery ->
                           checkoutQuery
                               .webUrl()

                           val gson = Gson()
                           var checkoutQuery= gson.toJson(checkoutQuery)
                           Log.e("test", "==checkoutQuery==" + checkoutQuery)
                       }
                       .userErrors { userErrorQuery: UserErrorQuery ->
                           userErrorQuery
                               .field()
                               .message()
                       }
               }
       }

       graphClient?.mutateGraph(query)!!.enqueue(object : GraphCall.Callback<Mutation> {
           override fun onResponse(response: GraphResponse<Mutation>) {
               try {
                   Log.e("test", "=res success==response=" + response)
                   val gson = Gson()
                   var test = gson.toJson(response)
                   Log.e("test", "==test==" + test)
//                        if (!response.data()!!.checkoutCreate.userErrors.isEmpty()) {
//                            // handle user friendly errors
//                        } else {
                   val checkoutId = response.data()!!.checkoutCreate.checkout.id.toString()
                   Log.e("bbb","=call start time=>>"+System.currentTimeMillis())

                   val checkoutWebUrl = response.data()!!.checkoutCreate.checkout.webUrl
                   if (checkoutWebUrl != null && !checkoutWebUrl.equals("")) {
                       webview.post(Runnable {
                           webview.loadUrl(checkoutWebUrl);
                       })

                   }
                   getOrderId(checkoutId)
                   Log.e("test", "=res success==checkoutId=" + checkoutId)
                   Log.e("test", "=res checkoutWebUrl==" + checkoutWebUrl)
                   //   }
               } catch (e: Exception) {
                   Log.e("test", "==e=" + e)
                   Log.e("test", "==e=" + e.localizedMessage)

               }

           }

           override fun onFailure(error: GraphError) {
               Log.e("test", "=res onFailure==checkoutId=" + error.localizedMessage)
           }
       })
        }

    }

    private fun getOrderId(checkoutId:String) {

        val queryOrderId = query { rootQuery: QueryRootQuery ->
            rootQuery
                .node(
                    ID(checkoutId)
                ) { nodeQuery: NodeQuery ->
                    nodeQuery
                        .onCheckout { checkoutQuery: CheckoutQuery ->
                            checkoutQuery
                                .order { orderQuery: OrderQuery ->
                                    orderQuery
                                        .orderNumber()
                                        .totalPrice().orderNumber()
                                }
                        }
                }
        }

        graphClient!!.queryGraph(queryOrderId).enqueue(
            object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(@NonNull response: GraphResponse<Storefront.QueryRoot>) {
                    val checkout = response.data()!!.node as Storefront.Checkout
                    if(checkout!=null && checkout.order!=null){
                        val orderId = checkout.order.orderNumber
                        Log.e("hhh","===163===orderId="+orderId)
                    }else{
                        Log.e("hhh","==order id not found=")
                        getOrderId(checkoutId)
                    }

                }

                override fun onFailure(@NonNull error: GraphError) {}

            },  null,
            RetryHandler.delay(30, TimeUnit.SECONDS)
                .whenResponse { response: GraphResponse<QueryRoot> ->
                    //Log.e("ttt","===response=="+response.data())

                    val checkout = response.data()!!.node as Storefront.Checkout
                    if(checkout!=null && checkout.order!=null){
                        val orderId = checkout.order.orderNumber
                        Log.e("ttt","===163===orderId="+orderId)
                        return@whenResponse true
                        //  Log.e("ttt","=call end time=>>"+System.currentTimeMillis())

                    }else{
                        Log.e("ttt","==order id not found=")
                        return@whenResponse false
                       // getOrderId(checkoutId)
                    }



                    (response.data()!!
                        .node as Checkout).order == null

                }
                .build())

    }

*//*
        val graphResponse: AtomicReference<GraphResponse<QueryRoot>> = AtomicReference()
        val graphError: AtomicReference<GraphError> = AtomicReference()


        val call: GraphCall<QueryRoot> = graphClient!!.queryGraph(query)

        call.enqueue(
            object : GraphCall.Callback<QueryRoot> {
                override fun onResponse(response: GraphResponse<QueryRoot>) {
                    graphResponse.set(response)
                    Log.e("hhh", "call get order id api")
                    val checkout = response.data()!!.node as Checkout
                    if(checkout.order.id!=null){
                        val orderId = checkout.order.id.toString()
                        Log.e("hhh", "OrderId: $orderId")
                    }else{
                        Log.e("hhh", "OrderId: not found")
                    }

                }

                override fun onFailure(error: GraphError) {
                    graphError.set(error)
                    Log.v("hhh", "error: " + error.message)
                }
            },
            null,
            RetryHandler.delay(10, TimeUnit.SECONDS)
                .maxCount(5)
                .whenResponse { response: GraphResponse<QueryRoot> ->
                    Log.e("hhh","===response=="+response.data())
                    var order=response.data()!!
                        .node as Checkout
                    if(order!=null){
                        var data=response.data()!!.node
                        val gson = Gson()
                        var test = gson.toJson(data)
                        Log.e("hhh","==190=response=="+test)
                    }else{
                        Log.e("hhh","==null=")
                    }
                    (response.data()!!
                        .node as Checkout).order == null

                }
                .build())*//*


    var graphClient:GraphClient?=null
      private fun initShopify() {

          var shop_domin="jshealth-vitamins-dev.myshopify.com"
          //  var api_key="92bb990a72178d571c37aff74e369475"
          var api_key="a650028e4ee6c4b47a8f07b7cbd4b6ca"
          graphClient= GraphClient.builder(this)

              .shopDomain(shop_domin)
              .accessToken(api_key)
              //  .httpClient(httpClient) // optional
              .httpCache(
                  File(getApplicationContext().getCacheDir(), "/http"),
                  10 * 1024 * 1024
              ) // 10mb for http cache
              // cached response valid by default for 5 minutes
              .build()

          val query = query { rootQuery: Storefront.QueryRootQuery ->
              rootQuery
                  .shop { shopQuery: Storefront.ShopQuery ->
                      shopQuery
                          .name()
                  }
          }
          val call = graphClient?.queryGraph(query)

          call?.enqueue(object : GraphCall.Callback<Storefront.QueryRoot?> {
              override fun onResponse(@NonNull response: GraphResponse<Storefront.QueryRoot?>) {
                  val name: String = response.data()!!.getShop().getName()
                  Log.e("res", "sucess name" + name)
              }

              override fun onFailure(@NonNull error: GraphError) {
                  Log.e("res", "Failed to execute query" + error)
              }
          })
      }*/
    }
}