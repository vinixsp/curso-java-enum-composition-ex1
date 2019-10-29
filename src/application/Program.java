package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Informe o nome do departamento: ");
		String department = sc.nextLine();
		
		System.out.println("Digite as informações do funcionário: ");
		System.out.print("Nome: ");
		String workerName = sc.nextLine();
		
		System.out.print("Nível: ");
		String workerLevel = sc.nextLine();
		
		System.out.print("Salário base: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(department));
		
		System.out.print("Quantos contratos esse funcionário possui? ");
		int contracts = sc.nextInt();
		
		for (int i=1; i<=contracts; i++) {
			System.out.println("\nDigite os dados do contrato #" + i);
			System.out.print("Data (dd/mm/yyyy): ");
			sc.nextLine();
			Date date = sdf.parse(sc.nextLine());
			System.out.print("Valor por hora: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duração (horas): ");
			double duration = sc.nextDouble();
			
			HourContract contract = new HourContract(date, valuePerHour, duration);
			worker.addContract(contract);
		}
		
		System.out.print("Digite o mês e o ano para calcular a renda (MM/YYYY): ");
		Calendar cal = Calendar.getInstance();
		sc.nextLine();
		String mes_ano = sc.nextLine();
		cal.setTime(sdf.parse("01/" + mes_ano));
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		System.out.println("Nome: " + worker.getName());
		System.out.println("Departamento: " + worker.getDepartment().getName());
		System.out.println("Renda para " + mes_ano + ": " + worker.income(month, year));
		
		sc.close();
	}

}
