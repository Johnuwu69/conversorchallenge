package com.aluracursos.conversorchallenge.principal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    //Ocultar valor de api jeje
    private String direccion ="https://v6.exchangerate-api.com/v6/";
    private String API = "1be41b45dad13cda1a8c2b1a";

    public static void main(String[] args){
        Principal principal = new Principal();
        principal.mostrarMenu();
    }

    private void mostrarMenu(){
        var opcion = -1;
        while (true){
            var menu = """
                    ***********************************************
                    1) Dolar = Peso argentino
                    2) Peso argentino = Dolar
                    3) Dolar = Real brasileño
                    4) Real brasileño = Dolar
                    5) Dolar = Peso colombiano
                    6) Peso colombiano = Dolar
                    7) Salir
                    
                    Elija una opcion valida
                    
                    ***********************************************
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    dolarAPesoArgentino();
                    break;
                case 2:
                    argADolar();
                    break;
                case 3:
                    dolarAReal();
                    break;
                case 4:
                    realADolar();
                    break;
                case 5:
                    dolarAPesoColombiano();
                    break;
                case 6:
                    pesoColombianoADolar();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Digite una opcion valida");
            }
        }
    }
    private void dolarAPesoArgentino(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [USD] corresponde al valor final de => " + cantidad*obtenerDivisa("USD", "ARS") + " [ARS]");

    }

    private void argADolar(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [ARS] corresponde al valor final de => " + cantidad*obtenerDivisa("ARS", "USD") + " [USD]");

    }

    private void dolarAReal(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [USD] corresponde al valor final de => " + cantidad*obtenerDivisa("USD", "BRL") + " [BRL]");

    }

    private void realADolar(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [BRL] corresponde al valor final de => " + cantidad*obtenerDivisa("BRL", "USD") + " [USD]");

    }

    private void dolarAPesoColombiano(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [USD] corresponde al valor final de => " + cantidad*obtenerDivisa("USD", "COP") + " [COP]");

    }

    private void pesoColombianoADolar(){
        System.out.println("Ingrese el valor que desea convertir");
        var cantidad = teclado.nextInt();
        System.out.println("El valor " + cantidad + " [COP] corresponde al valor final de => " + cantidad*obtenerDivisa("COP", "USD") + " [USD]");

    }

    private double obtenerDivisa(String from, String to){
        try{
            direccion = direccion + API+"/pair/"+ from + "/"+ to;
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            return jsonObject.get("conversion_rate").getAsDouble();
        }
        catch (IOException | InterruptedException e){
            System.out.println("Error al obtener datos del API" + e.getMessage());
            return -1;
        }

    }

}
