package com.example.ecommerce.paypal;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class paypalController {
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(){
        try{
        String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(10.0, "RM","paypal", "sale", "Payment Desription", cancelUrl, successUrl);

            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e){
            log.error("Error occurred! " ,e);
        }
        return new RedirectView("/payment/error");
    }

    //executing the payment success
    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentID") String paymentID,
            @RequestParam("payerID") String payerID
    ){
        try{
            Payment payment = paypalService.executrPayment(paymentID, payerID);
            if(payment.getState().equals("approved")){
                return "paymentSuccess";
            }
        }catch (PayPalRESTException e){
            log.error("Error occurred! " ,e);
        }
        return "paymentSuccess";
    }

    //executing the payment cancellation
    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    //executing the payment error
    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }

}

