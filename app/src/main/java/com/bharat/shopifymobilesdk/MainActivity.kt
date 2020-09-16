package com.bharat.shopifymobilesdk

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bharat.shopifymobilesdk.model.ChekoutMainRes
import com.bharat.shopifymobilesdk.model.ResponsMain
import com.google.gson.Gson
import com.shopify.buy3.GraphCallResult
import com.shopify.buy3.GraphClient
import com.shopify.buy3.GraphClient.Companion.build
import com.shopify.buy3.GraphError.*
import com.shopify.buy3.GraphResponse
import com.shopify.buy3.Storefront
import com.shopify.buy3.Storefront.*
import com.shopify.graphql.support.ID
import com.shopify.graphql.support.Input
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.getSettings().setJavaScriptEnabled(true);

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
                           ID("Z2lkOi8vc2hvcGlmeS9Qcm9kdWN0VmFyaWFudC8zMDk1NDY1MjMzNjIwNg==")
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
       graphClient!!.mutateGraph(query).enqueue { response: GraphCallResult<Mutation> ->
           if (response is GraphCallResult.Failure) {
               Log.e("test", "Call has been error -=> $response")
               val error =
                   (response as GraphCallResult.Failure).error
               if (error is CallCanceledError) {
                   Log.e("test", "Call has been canceled", error)
               } else if (error is HttpError) {
                   Log.e(
                       "test",
                       "Http request failed: " + (error as HttpError).message,
                       error
                   )
               } else if (error is NetworkError) {
                   Log.e("test", "Network is not available", error)
               } else if (error is ParseError) {
                   // in most cases should never happen
                   Log.e(
                       "test",
                       "Failed to parse GraphQL response",
                       error
                   )
               } else {
                   Log.e(
                       "test",
                       "Failed to due to other error ",
                       error
                   )
               }
           } else {

               try {
                   Log.e("test", "=res success==response=" + response)

                   val gson = Gson()
                   var test = gson.toJson(response)
                 //  gson.fromJson<ResponsMain>(test)
                   val responsMain: ResponsMain = Gson().fromJson(test, ResponsMain::class.java)
                   Log.e("test", "==test==" + test)
                    if(responsMain.getResponse()!=null){
                        if(responsMain.getResponse()!!.getData()!=null){
                            if(responsMain.getResponse()!!.getData()!!.getResponseData()!=null){
                                if(responsMain.getResponse()!!.getData()!!.getResponseData()!!.getCheckoutCreate()!=null){
                                    if(responsMain.getResponse()!!.getData()!!.getResponseData()!!.getCheckoutCreate()!!.getResponseData()!=null){
                                    if(responsMain.getResponse()!!.getData()!!.getResponseData()!!.getCheckoutCreate()!!.getResponseData()!!.getCheckout()!=null){
                                    if(responsMain.getResponse()!!.getData()!!.getResponseData()!!.getCheckoutCreate()!!.getResponseData()!!.getCheckout()!!.getResponseData()!=null){
                                        Log.e("test","=====not null===")
                                        var data= responsMain.getResponse()!!.getData()!!.getResponseData()!!.getCheckoutCreate()!!.getResponseData()!!.getCheckout()!!.getResponseData()
                                         if(data!=null){
                                             var web_url=data!!.getWebUrl()

                                             if(!web_url.equals("")){
                                                 Log.e("test","=====load web url=")
                                                 webview.post(Runnable {
                                                     webview.loadUrl(web_url);
                                                 })
                                             }else{
                                                 Log.e("test","=====null =web_url==136=")

                                             }
                                             var idData=data!!.getId()
                                             if(idData!=null){
                                                 if(!idData.getId().equals("")){
                                                     Log.e("test","=====call get order api=")

                                                     getOrderId(idData.getId()+"")
                                                 }else{
                                                     Log.e("test","=====null =checkout id==145=")
                                                 }
                                             }else{
                                                 Log.e("test","=====null =getId==146=")
                                             }
                                         }else{
                                             Log.e("test","=====null =data==144=")
                                         }

                                    }else{
                                        Log.e("test","=====null =getResponseData==127=")
                                    }

                                    }else{
                                        Log.e("test","=====null =getCheckout==129=")
                                    }

                                    }else{
                                        Log.e("test","=====null =getResponseData===133=")
                                    }
                                }else{
                                    Log.e("test","=====null =getCheckoutCreate===136=")
                                }

                                }else{
                                Log.e("test","=====null =getResponseData===140=")
                            }
                        }else{
                            Log.e("test","=====null =getData===143==")
                        }

                    }else{
                        Log.e("test","=====null =getResponse===147==")
                    }

               } catch (e: Exception) {
                   Log.e("test", "==e=" + e)
                   Log.e("test", "==e=" + e.localizedMessage)

               }
           }
       }

    /*   graphClient?.mutateGraph(query)!!.enqueue(object : GraphCallResult<Mutation> {
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
                   Log.e("bbb", "=call start time=>>" + System.currentTimeMillis())

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
       })*/
        }

    }

    private fun getOrderId(checkoutId: String) {

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

        graphClient!!.queryGraph(queryOrderId).enqueue { response: GraphCallResult<QueryRoot> ->
            if (response is GraphCallResult.Failure) {
                Log.e("test", "Call has been error -=> $response")
                val error =
                    (response as GraphCallResult.Failure).error
                if (error is CallCanceledError) {
                    Log.e("test", "Call has been canceled", error)
                } else if (error is HttpError) {
                    Log.e(
                        "test",
                        "Http request failed: " + (error as HttpError).message,
                        error
                    )
                } else if (error is NetworkError) {
                    Log.e("test", "Network is not available", error)
                } else if (error is ParseError) {
                    // in most cases should never happen
                    Log.e(
                        "test",
                        "Failed to parse GraphQL response",
                        error
                    )
                } else {
                    Log.e(
                        "test",
                        "Failed to due to other error ",
                        error
                    )
                }
            } else {
                val gson = Gson()
                var test = gson.toJson(response)
                Log.e("test","=====Order_ID====="+test)
                val chekoutMainRes: ChekoutMainRes = Gson().fromJson(test, ChekoutMainRes::class.java)
                 if(chekoutMainRes!=null){
                     if(chekoutMainRes!!.getResponse()!=null){
                         if(chekoutMainRes!!.getResponse()!!.getData()!=null){
                             if(chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!=null){
                                 if(chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!!.getNode()!=null){
                                     if(chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!!.getNode()!!.getResponseData()!=null){
                                         if(chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!!.getNode()!!.getResponseData()!!.getOrder()!=null){
                                         if(chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!!.getNode()!!.getResponseData()!!.getOrder()!!.getResponseData()!=null){
                                            var orderDetail= chekoutMainRes!!.getResponse()!!.getData()!!.getResponseData()!!.getNode()!!.getResponseData()!!.getOrder()!!.getResponseData()
                                            if(orderDetail!!.getOrderNumber()!=null && !orderDetail!!.getOrderNumber().equals("")){
                                                Log.e("test","=====getOrderNumber Got======"+orderDetail!!.getOrderNumber())
                                              Log.e("test","===getTotalPrice==>"+orderDetail.getTotalPrice())
                                                var orderIdRes=orderDetail!!.getId()
                                                if(orderIdRes!=null){
                                                    var order_id=orderIdRes.getId()
                                                    Log.e("test","===get Order Id==>"+orderIdRes.getId())

                                                }
                                            }else{
                                                getOrderId(checkoutId)
                                            }
                                         }

                                         }else{
                                             getOrderId(checkoutId)
                                         }

                                         }else{
                                             getOrderId(checkoutId)
                                         }
                                     }else{
                                         getOrderId(checkoutId)
                                     }
                                 }else{
                                     getOrderId(checkoutId)
                                 }
                             }else{
                                 getOrderId(checkoutId)
                             }
                         }else{
                             getOrderId(checkoutId)
                         }
                     }else{
                         getOrderId(checkoutId)
                     }
                 }
            }



      /*  graphClient!!.queryGraph(queryOrderId).enqueue(
            object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(@NonNull response: GraphResponse<Storefront.QueryRoot>) {
                    val checkout = response.data()!!.node as Storefront.Checkout
                    if (checkout != null && checkout.order != null) {
                        val orderId = checkout.order.orderNumber
                        Log.e("hhh", "===163===orderId=" + orderId)
                    } else {
                        Log.e("hhh", "==order id not found=")
                        getOrderId(checkoutId)
                    }

                }

                override fun onFailure(@NonNull error: GraphError) {}

            }, null,
            RetryHandler.delay(30, TimeUnit.SECONDS)
                .whenResponse { response: GraphResponse<QueryRoot> ->
                    //Log.e("ttt","===response=="+response.data())

                    val checkout = response.data()!!.node as Storefront.Checkout
                    if (checkout != null && checkout.order != null) {
                        val orderId = checkout.order.orderNumber
                        Log.e("ttt", "===163===orderId=" + orderId)
                        return@whenResponse true
                        //  Log.e("ttt","=call end time=>>"+System.currentTimeMillis())

                    } else {
                        Log.e("ttt", "==order id not found=")
                        return@whenResponse false
                        // getOrderId(checkoutId)
                    }



                    (response.data()!!
                        .node as Checkout).order == null

                }
                .build())*/

    }

/*
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
                .build())*/


    var graphClient:GraphClient?=null
      private fun initShopify() {
          var shop_domin="jshealth-vitamins-dev.myshopify.com"
          //  var api_key="92bb990a72178d571c37aff74e369475"
          var api_key="a650028e4ee6c4b47a8f07b7cbd4b6ca"
         //var tet= GraphClient.Config()
          graphClient = build(
              this, shop_domin, api_key
          ); { builder: GraphClient.Config? -> }



          val query = query { rootQuery: Storefront.QueryRootQuery ->
              rootQuery
                  .shop { shopQuery: Storefront.ShopQuery ->
                      shopQuery
                          .name()
                  }
          }

          graphClient!!.queryGraph(query).enqueue { response: GraphCallResult<QueryRoot> ->
              if (response is GraphCallResult.Failure) {
                  Log.e("test", "Call has been error -=> $response")
                  val error =
                      (response as GraphCallResult.Failure).error
                  if (error is CallCanceledError) {
                      Log.e("test", "Call has been canceled", error)
                  } else if (error is HttpError) {
                      Log.e(
                          "test",
                          "Http request failed: " + (error as HttpError).message,
                          error
                      )
                  } else if (error is NetworkError) {
                      Log.e("test", "Network is not available", error)
                  } else if (error is ParseError) {
                      // in most cases should never happen
                      Log.e(
                          "test",
                          "Failed to parse GraphQL response",
                          error
                      )
                  } else {
                      Log.e(
                          "test",
                          "Failed to due to other error ",
                          error
                      )
                  }
              } else {
                  Log.e(
                      "test",
                      "Call has been success response => $response"
                  )
              }
          }
      }
}