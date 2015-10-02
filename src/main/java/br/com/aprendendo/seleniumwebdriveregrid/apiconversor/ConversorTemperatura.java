package br.com.aprendendo.seleniumwebdriveregrid.apiconversor;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Esta classe tem como objetivo fornecer resultados de cálculos de conversão de temperatura
public class ConversorTemperatura {
	
	// Declaração de variáveis
	private final static Integer SCALE = 6;
	private static Double valor = null;

	// Método que aplica a escala no valor Double, por exemplo, o resultado do
	// valor Double 51.12313121323123, aplicando o método neste valor, o
	// resultado ficaria 51.123131, ou seja, com no máximo 6 dígitos após a vírgula
	public static Double aplicarScala(Double valor) {
		// Atribui um BigDecimal que recebendo o valor de um Double e que ao
		// final é lhe aplicado um escala
		BigDecimal bd = new BigDecimal(valor).setScale(SCALE,
				RoundingMode.HALF_EVEN);
		// retorna o valor em Double
		return bd.doubleValue();
	}

	// Conversão do valor de Celsius para Fahrenheit
	public static String celsiusParaFahrenheit(String celsius) {
		// Atribui o valor em Double convertida de uma String
		valor = Double.parseDouble(celsius);
		// Retorna uma String convertida de um valor Double onde lhe foi
		// aplicado uma escala 
		return String.valueOf(aplicarScala((1.8 * valor) + 32.0));
	}

	// Conversão do valor de Fahrenheit para Celsius
	public static String fahrenheitParaCelsius(String fahrenheit) {
		// Atribui o valor em Double convertida de uma String
		valor = Double.parseDouble(fahrenheit);
		// Retorna uma String convertida de um valor Double onde lhe foi
		// aplicado uma escala
		return String.valueOf(aplicarScala((5.0 * (valor - 32.0) / 9.0)));
	}

}