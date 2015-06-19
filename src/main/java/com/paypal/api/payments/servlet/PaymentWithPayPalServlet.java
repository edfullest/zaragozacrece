// #Create Payment Using PayPal Sample
// This sample code demonstrates how you can process a
// PayPal Account based Payment.
// API used: /v1/payments/payment
package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.api.payments.util.GenerateAccessToken;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;
import javax.servlet.http.HttpSession;

/**
 * @author lvairamani
 *
 */
public class PaymentWithPayPalServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    boolean bandera=false;
    private static final Logger LOGGER = Logger
            .getLogger(PaymentWithPayPalServlet.class);
    Map<String, String> map = new HashMap<String, String>();
    
    public void init(ServletConfig servletConfig) throws ServletException {
        // ##Load Configuration
        // Load SDK configuration for
        // the resource. This intialization code can be
        // done as Init Servlet.
        InputStream is = PaymentWithPayPalServlet.class
                .getResourceAsStream("/sdk_config.properties");
        try {
            PayPalResource.initConfig(is);
        } catch (PayPalRESTException e) {
            LOGGER.fatal(e.getMessage());
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
        
    }
    
    // ##Create
    // Sample showing to create a Payment using PayPal
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        createPayment(req, resp);
        HttpSession session = req.getSession();
        String tipo = (String)session.getAttribute("tipo");
        
        if(tipo!= null && (tipo.equals("renovar") || tipo.equals("activar"))){
            req.getRequestDispatcher("suscripciones").forward(req, resp);
            
        }
        
        else{
            req.setAttribute("tipoPago","paypal");
            req.getRequestDispatcher("pagar").forward(req, resp);
        }
        
        
        
    }
    
    public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) {
        Payment createdPayment = null;
        // ###AccessToken
        // Retrieve the access token from
        // OAuthTokenCredential by passing in
        // ClientID and ClientSecret
        APIContext apiContext = null;
        String accessToken = null;
        try {
            System.out.println("oos");
            accessToken = GenerateAccessToken.getAccessToken();
            
            // ### Api Context
            // Pass in a `ApiContext` object to authenticate
            // the call and to send a unique request id
            // (that ensures idempotency). The SDK generates
            // a request id if you do not pass one explicitly.
            apiContext = new APIContext(accessToken);
            // Use this variant if you want to pass in a request id
            // that is meaningful in your application, ideally
            // a order id.
            /*
            * String requestId = Long.toString(System.nanoTime(); APIContext
            * apiContext = new APIContext(accessToken, requestId ));
            */
        } catch (PayPalRESTException e) {
            System.out.println("oos999");
            req.setAttribute("error", e.getMessage());
        }
        if (req.getParameter("PayerID") != null) {
            System.out.println("oos2");
            
            
            Payment payment = new Payment();
            if (req.getParameter("guid") != null) {
                System.out.println("oos2.1");
                payment.setId(map.get(req.getParameter("guid")));
                System.out.println("oos2.2");
            }
            System.out.println("oos2.3");
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(req.getParameter("PayerID"));
            System.out.println("oos2.4");
            try {
                System.out.println("oos2.5");
                
                createdPayment = payment.execute(apiContext, paymentExecution);
                System.out.println("oos2.6");
                ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), Payment.getLastResponse(), null);
                System.out.println("oos2.7");
                HttpSession session = req.getSession();
                session.removeAttribute("error");
                session.setAttribute("exitoso", true);
                
                String tipo = (String)session.getAttribute("tipoApadrinamiento");
                System.out.println(tipo);
                req.setAttribute("tipo", tipo);
                session.removeAttribute("tipoApadrinamiento");
                
                bandera=true;
                
            } catch (PayPalRESTException e) {
                System.out.println("oos2.8");
                System.out.println(Payment.getLastRequest());
                System.out.println(e.getMessage());
                HttpSession session = req.getSession();
                session.setAttribute("error", e.getMessage());
                
                
                
            }
        } else {
            System.out.println("oos5");
            HttpSession session = req.getSession();
            String tipo = req.getParameter("tipo");
            
            if(tipo==null){
                tipo = (String)session.getAttribute("tipo");
            }
            
            boolean suscripcionPareja = (Boolean)session.getAttribute("suscripcionPareja");
            
            
            // ###Amount
            // Let's you specify a payment amount.
            Amount amount = new Amount();
            amount.setCurrency("MXN");
            // Total must be equal to sum of shipping, tax and subtotal.
            System.out.println(tipo);
            if(tipo!=null && tipo.equals("apadrinasolo")){
                amount.setTotal("750");
                req.setAttribute("tipo", "apadrinasolo");
                session.setAttribute("tipoApadrinamiento", "apadrinasolo");
            }
            
            else if(tipo!=null && tipo.equals("apadrinapareja")) {
                amount.setTotal("375");
                req.setAttribute("tipo", "apadrinapareja");
                session.setAttribute("tipoApadrinamiento", "apadrinapareja");
                
            }
            
            else if (tipo!=null && tipo.equals("renovar")){
                if(suscripcionPareja){
                    amount.setTotal("375");
                }
                else{
                    amount.setTotal("750");
                }
            }
            
            else if (tipo!=null && tipo.equals("activar")){
                if(suscripcionPareja){
                    amount.setTotal("375");
                }
                else{
                    amount.setTotal("750");
                }
                
            }
            
            
            // ###Transaction
            // A transaction defines the contract of a
            // payment - what is the payment for and who
            // is fulfilling it. Transaction is created with
            // a `Payee` and `Amount` types
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction
                    .setDescription("This is the payment transaction description.");
            
         
            String correo = (String)session.getAttribute("correo");
            String thiscorreo = (String)session.getAttribute("thiscorreo");
            // ### Items
            Item item = new Item();
            
                       
            if(tipo!=null && tipo.equals("apadrinasolo")){
                
                item.setName("Apadrinamiento solo "+thiscorreo).setQuantity("1").setCurrency("MXN").setPrice("750");
                
            }
            
            else if(tipo!=null && tipo.equals("apadrinapareja")) {
                item.setName("Apadrinamiento en Pareja con "+correo).setQuantity("1").setCurrency("MXN").setPrice("375");
                
            }
            
            else if (tipo!=null && tipo.equals("renovar")){
                if(suscripcionPareja){
                    item.setName("Renovar suscripción en Pareja con "+correo).setQuantity("1").setCurrency("MXN").setPrice("375");
                }
                else{
                    item.setName("Renovar suscripción de"+thiscorreo).setQuantity("1").setCurrency("MXN").setPrice("750");
                }
            }
            
            else if (tipo!=null && tipo.equals("activar")){
                if(suscripcionPareja){
                    item.setName("Activar suscripcion en Pareja con "+correo).setQuantity("1").setCurrency("MXN").setPrice("375");
                }
                else{
                    item.setName("Activar suscripción de"+thiscorreo).setQuantity("1").setCurrency("MXN").setPrice("750");
                }
                
            }
            
            
            
            ItemList itemList = new ItemList();
            List<Item> items = new ArrayList<Item>();
            items.add(item);
            itemList.setItems(items);
            
            transaction.setItemList(itemList);
            
            
            // The Payment creation API requires a list of
            // Transaction; add the created `Transaction`
            // to a List
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);
            
            // ###Payer
            // A resource representing a Payer that funds a payment
            // Payment Method
            // as 'paypal'
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");
            
            // ###Payment
            // A Payment Resource; create one using
            // the above types and intent as 'sale'
            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);
            
            
             // ###Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            if(tipo!=null && tipo.equals("apadrinasolo")){
                redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?tipo=apadrinasolo&guid=" + guid);
            
            
            
            
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?tipo=apadrinasolo&guid=" + guid);
                
            }
            
            else if (tipo!=null && tipo.equals("apadrinapareja"))  {
                      redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?tipo=apadrinapareja&guid=" + guid);
            
            
            
            
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?tipo=apadrinapareja&guid=" + guid);
                
            }
            
            else{
                
                redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?&guid=" + guid);
            
            
            
            
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/pagarPaypal?&guid=" + guid);
                
            }
           
            
            
            
            payment.setRedirectUrls(redirectUrls);
            
            
            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                System.out.println("oos6");
                createdPayment = payment.create(apiContext);
                LOGGER.info("Created payment with id = "
                        + createdPayment.getId() + " and status = "
                        + createdPayment.getState());
                // ###Payment Approval Url
                Iterator<Links> links = createdPayment.getLinks().iterator();
                
                System.out.println("oos7");
                while (links.hasNext()) {
                    Links link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        session.setAttribute("redirectURL", link.getHref());
                        
                    }
                }
                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), Payment.getLastResponse(), null);
                System.out.println("oos8");
                map.put(guid, createdPayment.getId());
                System.out.println("oos9");
            } catch (PayPalRESTException e) {
                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
            }
        }
        if(bandera){
            bandera=false;
        }
        else{
            
            req.setAttribute("pago","nuevo");
            
            
        }
        
        return createdPayment;
    }
}
