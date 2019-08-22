package Data;
/**
 * @author n231013341 - Matheus Murta
 * @date 22/08/2019
 * @e-mail matheus.murta322@gmail.com
 * @github matbeavis 
 * @discord #7458
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Data.Console;

public class dataUtils {

	public static void main(String[] args) {
		int opcao;

		do {
			System.out.println("\n=============Menu - Manipulação de Datas=============");
			System.out.println("0 - Finalizar programa;");
			System.out.println("1 - Validar data do usuário;");
			System.out.println("2 - Calcular diferença entre datas; ");
			System.out.println("3 - Calcular diferença entre datas em horas; ");
			System.out.println("4 - Ajustar fim de semana; ");
			System.out.println("5 - Calcular data; ");
			System.out.println("6 - Informar dia da semana; ");
			System.out.println("7 - Informar quantidade de dias do mês; ");
			System.out.println("8 - Exibir calendário completo do mês; ");
			System.out.println("9 - Verificar se o ano é bissexto; ");
			System.out.println("10- Informar primeiro dia útil do mês. ");

			opcao = Console.readInt("Digite a opção desejada: ");
			switch (opcao) {
			case 0:
				System.out.println("Programa finalizado.");
				break;
			case 1:
				validarData();
				break;
			case 2:
				calcularDiferenca();
				break;
			case 3:
				calcularDiferencaHoras();
				break;
			case 4:
				ajustarFimDeSemana();
				break;
			case 5:
				calcularData();
				break;
			case 6:
				informarDia();
				break;
			case 7:
				informarQuantDiasMes();
				break;
			case 8:
				exibirCalendario();
				break;
			case 9:
				verificarAnoBissexto();
				break;
			case 10:
				primeiroDiaUtil();
				break;
			default:
				System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

	}

	private static void validarData() {
		System.out.println("\n=============1 - Validar data do usuário=============");
		char hora = 'n';
		if (dataValida(Console.readLine("Digite a data: "), hora)) {
			System.out.println("Data válida.");
		}
	}

	private static Boolean dataValida(String date, char hora) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			if (hora == 'n') {
				sdf = new SimpleDateFormat("dd/MM/yyyy");
			}
			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			System.out.println("Data inválida.");
			return false;
		}
	}

	/**
	 * metodo para converter string em Date
	 */
	private static Date stringToDate(String date, char hora) {
		Date data = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			if (hora == 'n') {
				sdf = new SimpleDateFormat("dd/MM/yyyy");
			}
			sdf.setLenient(false);
			data = sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("Data inválida.");
			return null;
		}
		return data;
	}

	/**
	 * metodo para converter Date em Calendar
	 */
	private static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * metodo para calcular a diferença em dias entre duas datas
	 */
	private static void calcularDiferenca() {
		System.out.println("\n=============2 - Calcular diferença em dias==========");

		Boolean valida;
		String d1, d2;
		char hora = 'n';
		Calendar data1 = null, data2 = null;
		do {

			// recebe String, converte String em Date, Converte Date em Calendar
			do {
				d1 = Console.readLine("Digite a primeira data: ");
				valida = dataValida(d1, hora);
				if (!valida) {
					continue;
				} else {
					data1 = dateToCalendar(stringToDate(d1, hora));
				}
			} while (!valida);

			do {
				d2 = Console.readLine("Digite a segunda data: ");
				valida = dataValida(d2, hora);
				if (!valida) {
					continue;
				} else {
					data2 = dateToCalendar(stringToDate(d2, hora));
				}
			} while (!valida);

			// data1 = dateToCalendar(stringToDate(Console.readLine("Digite a
			// primeira data: "), hora));
			// data2 = dateToCalendar(stringToDate(Console.readLine("Digite a
			// segunda data: "), hora));

			// data1.before(data2) retorna true se data1 vem antes da data2

			if (!(data1.before(data2))) {
				System.out.println("A primeira data deve ser antes que a segunda.");
				continue;
			} else {
				// declarando a data1 como data inicial
				Calendar start = Calendar.getInstance();
				start = data1;

				// declarando a data2 como data final
				Calendar end = Calendar.getInstance();
				end = data2;

				// seta as horas dos dois dias para meia noite
				start.set(Calendar.HOUR_OF_DAY, 0);
				start.set(Calendar.MINUTE, 0);
				start.set(Calendar.SECOND, 0);
				start.set(Calendar.MILLISECOND, 0);

				end.set(Calendar.HOUR_OF_DAY, 0);
				end.set(Calendar.MINUTE, 0);
				end.set(Calendar.SECOND, 0);
				end.set(Calendar.MILLISECOND, 0);

				// calculo: subtrai a diferença de milissegundos entre a data
				// final e a data inicial, converte o resultado em dias
				long diferenca = TimeUnit.MILLISECONDS
						.toDays(Math.abs(end.getTimeInMillis() - start.getTimeInMillis()));
				if (diferenca >= 365) {
					System.out.println("A diferença entre as datas é de: " + diferenca + " dia(s), o que equivale a "
							+ diferenca / 365 + " ano(s).");
				} else {
					System.out.println("A diferença entre as datas em dias é de: " + diferenca + " dia(s).");
				}
			}
		} while (!(data1.before(data2)));
	}

	/**
	 * metodo para calcular a diferenca em horas entre duas datas
	 */
	private static void calcularDiferencaHoras() {
		System.out.println("\n=============3 - Calcular diferença em horas=========");
		char hora = 's';
		String d1, d2;
		Boolean valida;
		Calendar data1 = null, data2 = null;
		do {
			// recebe String, converte String em Date, converte Date em Calendar
			do {
				d1 = Console.readLine("Digite a primeira data: ");
				valida = dataValida(d1, hora);
				if (!valida) {
					continue;
				} else {
					data1 = dateToCalendar(stringToDate(d1, hora));
				}
			} while (!valida);

			do {
				d2 = Console.readLine("Digite a segunda data: ");
				valida = dataValida(d2, hora);
				if (!valida) {
					continue;
				} else {
					data2 = dateToCalendar(stringToDate(d2, hora));
				}
			} while (!valida);

			// data1 = dateToCalendar(stringToDate(Console.readLine("Digite a
			// primeira data: "), comHora));
			// data2 = dateToCalendar(stringToDate(Console.readLine("Digite a
			// segunda data: "), comHora));

			// true se data1 vem antes da data2
			if (!(data1.before(data2))) {
				System.out.println("A primeira data deve ser antes que a segunda.");
				continue;
			}
		} while (!(data1.before(data2)));

		// calculo: subtrai a diferença de milissegundos entre a data1 e a
		// data2, converte o resultado em horas
		System.out.println("A diferença entre as datas em horas é de: "
				+ TimeUnit.MILLISECONDS.toHours(Math.abs(data1.getTimeInMillis() - data2.getTimeInMillis()))
				+ " hora(s).");
	}

	/**
	 * metodo para ajustar a data para o proximo dia util
	 */
	private static void ajustarFimDeSemana() {
		System.out.println("\n=============4 - Ajustar fim de semana===============");
		String data = "";
		Calendar date = null;
		Boolean valida;
		char hora = 'n';
		do {
			data = Console.readLine("Digite a data: ");
			valida = dataValida(data, hora);
			if (!valida) {
				continue;
			} else {
				date = dateToCalendar(stringToDate(data, hora));
			}

			int dia = date.get(Calendar.DAY_OF_WEEK);

			// o índice de DAY_OF_WEEK começa no 1
			if (dia == 1 || dia == 7) {
				if (dia == 1) {
					System.out.println("A data inserida é um domingo.");
					date.add(Calendar.DATE, 1);
				} else {
					System.out.println("A data inserida é um sábado");
					date.add(Calendar.DATE, 2);
				}
			}
			dia = date.get(Calendar.DAY_OF_WEEK);
			System.out.println("A nova data é: " + date.getTime() + "\nDia da semana: segunda.");
		} while (!valida);
	}

	/**
	 * método para adicionar dias a uma data
	 */
	private static void calcularData() {
		System.out.println("\n=============5 - Calcular data=======================");

		String d;
		int dias = 0;
		char hora = 'n';
		Boolean valida;
		Calendar data = null;
		// recebe String, converte String em Date, converte Date em Calendar
		do {
			d = Console.readLine("Digite a data: ");
			valida = dataValida(d, hora);
			if (!valida) {
				continue;
			} else {
				data = dateToCalendar(stringToDate(d, hora));
			}
		} while (!valida);

		do {
			dias = Console.readInt("Digite o número de dias que deseja adicionar à data: ");
			if (dias <= 0) {
				System.out.println("O número de dias deve ser maior que zero.");
				continue;
			}
		} while (dias <= 0);

		// adicionado o numero de dias à data
		data.add(Calendar.DATE, dias);
		System.out.println("Nova data adicionando " + dias + " dia(s): " + data.getTime());
	}

	/**
	 * metodo para informa
	 */
	private static void informarDia() {
		System.out.println("\n=============6 - Informar dia da semana==============");

		String data;
		boolean valida;
		int dia;
		char hora = 'n';
		String diaSemana[] = { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" };

		do {
			data = Console.readLine("Digite a data: ");
			valida = dataValida(data, hora);
			if (!valida) {
				continue;
			} else {
				int mes = Integer.parseInt(data.substring(3, 5));
				int i;
				for (i = 0; i < 12; i++) {
					if (i == mes - 1) {
						dia = diaDaSemana(Integer.parseInt(data.substring(0, 2)), i + 1,
								Integer.parseInt(data.substring(6)));
						for (int j = 0; j < 7; j++) {
							if (j == dia) {
								System.out
										.println("O dia da semana da data " + data + " é um(a) " + diaSemana[j] + ".");
								break;
							}
						}
					}
				}

			}
		} while (!valida);

	}

	/**
	 * metodo para informar o numero de dias do mês
	 */
	private static void informarQuantDiasMes() {
		System.out.println("\n=============7 - Quantidade de dias do mês===========");

		String meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };

		String mes = Console.readLine("Digite o mês: ");

		// loop percorre o vetor dos meses comparando com o mes inserido, quando
		// encontra imprime o numero de dias do mes
		// no caso de fevereiro, verifica se o ano é bissexto
		for (int i = 0; i < meses.length; i++) {
			if (meses[i].equalsIgnoreCase(mes)) {
				if (i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 10) {
					System.out.println("O mês de " + meses[i] + " tem 31 dias.");
				} else if (i == 1) {
					int ano = Console.readInt("Informe o ano: ");
					if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
						System.out.println("O mês de fevereiro em ano bissexto tem 29 dias.");
					} else {
						System.out.println("O mês de fevereiro em ano não bissexto tem 28 dias.");
					}
				} else {
					System.out.println("O mês de " + meses[i] + " tem 30 dias.");
				}
			}
		}

	}

	/**
	 * metodo para exibir o calendário do mês
	 */
	private static void exibirCalendario() {
		System.out.println("\n=============8 - Exibir calendário do mês============");

		String meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		String dias[] = { "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" };
		int nDias[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String mes = Console.readLine("Digite o mês: ");
		int ano = Console.readInt("Digite o ano: ");

		// calculo para verificar se o ano é bissexto
		if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
			nDias[1] = 29;
		}
		System.out.println();
		int diaSemana = 0, i;

		// impressao do calendario
		for (i = 0; i < 12; i++) {
			if (mes.equalsIgnoreCase(meses[i])) {
				System.out.println(String.format("%15s", "") + meses[i] + "  " + ano);
				System.out.println();
				break;
			}
		}

		// impressao dos nomes dos dias da semana
		for (int j = 0; j < 7; j++)
			System.out.print(String.format("%3s", "") + dias[j]);
		System.out.println(" ");

		// impressao dos dias da semana
		diaSemana = diaDaSemana(1, i + 1, ano);
		for (int k = 1; k <= diaSemana; k++)
			System.out.print(String.format("%6s", ""));
		for (int l = 0; l < nDias[i]; l++) {
			System.out.print(String.format("%" + (4 + 2 - String.valueOf(l + 1).length()) + "s", "") + (l + 1));
			if ((l + diaSemana + 1) % 7 == 0)
				System.out.println();
		}
		System.out.println(" ");
	}

	/*
	 * metodo para calcular o dia da semana
	 */
	private static int diaDaSemana(int dia, int mes, int ano) {
		String data = dia + "/" + mes + "/" + ano;
		char hora = 'n';

		Calendar calendario = dateToCalendar(stringToDate(data, hora));
		int diaDaSem = calendario.get(Calendar.DAY_OF_WEEK);
		;// 6 = sexta
		return diaDaSem - 1;
	}

	/**
	 * metodo para verificar se o ano inserido é bissexto
	 */
	private static void verificarAnoBissexto() {
		System.out.println("\n=============9 - Verificar se o ano é bissexto=======");
		String ano = "";
		boolean valido = true;
		Date data = new Date();
		do {
			do {
				ano = Console.readLine("Digite o ano: ");
				if (ano.length() != 4) {
					System.out.println("Ano inválido.");
					valido = false;
				} else {
					try {
						Integer.parseInt(ano);
						valido = true;
					} catch (NumberFormatException e) {
						System.out.println("Ano inválido.");
						valido = false;
					}
				}
			} while (!valido);

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				sdf.setLenient(false);
				data = sdf.parse(ano);
				valido = true;
			} catch (ParseException e) {
				System.out.println("Ano inválido.");
				valido = false;
			}

			if (!valido) {
				continue;
			} else {
				Calendar year = dateToCalendar(data);
				year.set(Calendar.YEAR, (Integer.parseInt(ano)));
				// se o numero de dias do ano for maior que 365 o ano é
				// bissexto
				if (year.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
					System.out.println("O ano " + ano + " é bissexto.");
				} else {
					System.out.println("O ano " + ano + " não é bissexto.");
				}
			}

		} while (!valido);

	}

	/**
	 * metodo para imprimir o primeiro dia util do mês inserido
	 */
	private static void primeiroDiaUtil() {
		System.out.println("\n=============10- Primeiro dia útil do mês============");

		String meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		String dias[] = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
		String mes = Console.readLine("Digite o mês: ");
		int ano = Console.readInt("Digite o ano: ");
		int dia = 0, i;
		for (i = 0; i < 12; i++) {
			if (meses[i].equalsIgnoreCase(mes)) {
				dia = diaDaSemana(1, i + 1, ano);
				break;
			}
		}
		
		if (dia == 0 || dia == 6) {
			dia = 1;
		}
		
		System.out.println("O primeiro dia útil do mês de " + meses[i] + " é um(a) " + dias[dia] + ".");

	}

}
