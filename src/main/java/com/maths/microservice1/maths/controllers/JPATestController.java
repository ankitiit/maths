package com.maths.microservice1.maths.controllers;

import com.google.common.collect.Lists;
import com.maths.microservice1.maths.jpa.entity.Customer;
import com.maths.microservice1.maths.jpa.repository.CustomerRepository;
import mathsacademy.protobuf.customer.model.CustomerProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.soap.*;
import java.util.List;

@RestController
//@RefreshScope
public class JPATestController {

   // @Value("${msg}")
    private String message = "Ankit";

    @Autowired
    CustomerRepository repository;


    @Autowired
    RestTemplate restTemplate;



    @RequestMapping(value ="/protobuf", produces = "application/x-protobuf")
    public CustomerProto.Account getProtobuf() {


       /* String response = restTe mplate.exchange("http://TyagiMathsAcademy-Rest/accounts",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();*/


        /*ResponseEntity<CustomerProto.Account> customer = restTemplate.getForEntity(
                "http://TYAGIMATHSACADEMY-REST/accounts", CustomerProto.Account.class);

        System.out.println("customer retrieved: " + customer.toString());*/

       // return customer.getBody();
        return null;
    }


    @RequestMapping("/api/hi")
    public String hi() {

        String soapEndpointUrl = "https://www.w3schools.com/xml/tempconvert.asmx";
        String soapAction = "https://www.w3schools.com/xml/CelsiusToFahrenheit";

        callSoapWebService(soapEndpointUrl, soapAction);


        return "Hello World from Restful Ankit's API using service discovery"+ this.message;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public void saveCustomers() {
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Customer> getCustomers() {

        return Lists.newArrayList(repository.findAll());
    }


    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "myNamespace";
        String myNamespaceURI = "https://www.w3schools.com/xml/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="https://www.w3schools.com/xml/">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:CelsiusToFahrenheit>
                        <myNamespace:Celsius>100</myNamespace:Celsius>
                    </myNamespace:CelsiusToFahrenheit>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("CelsiusToFahrenheit", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Celsius", myNamespace);
        soapBodyElem1.addTextNode("100");
    }


    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }


    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }



}
