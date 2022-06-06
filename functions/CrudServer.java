package functions;

import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import crudJava.Conexao;

public interface CrudServer {

    public static void addEmployee(int empNum, List<Employee> arrEmployees, Employee emp, boolean found,
            Socket clientSocket) throws IOException {
        for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext();) {
            Employee e = i.next();
            if (e.getEmpID() == emp.getEmpID()) {
                found = true;
            }
        }
        if (!found) {
            arrEmployees.add(emp);
        } else {
            Conexao.enviar(clientSocket, "\nFuncionário não cadastrado.\nJá existe um funcionário com esse ID.");
        }
    }

    public static void searchBySalary(int empNum, List<Employee> arrEmployees, boolean found, Socket clientSocket) throws IOException {
        empNum = Conexao.receberInt(clientSocket);
        String str = "";
        for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext();) {
            Employee e = i.next();
            switch (empNum) {
                case 1:
                    if (e.getEmpSalary() >= 810 && e.getEmpSalary() <= 1400) {
                        str = str + "]" + e.toString();
                        found = true;
                    }
                    break;

                case 2:
                    if (e.getEmpSalary() >= 2000 && e.getEmpSalary() <= 3500) {
                        str = str + "]" + e.toString();
                        found = true;
                    }
                    break;

                case 3:
                    if (e.getEmpSalary() >= 4000 && e.getEmpSalary() <= 5000) {
                        str = str + "]" + e.toString();
                        found = true;
                    }
                    break;

                case 4:
                    if (e.getEmpSalary() >= 5500) {
                        str = str + "]" + e.toString();
                        found = true;
                    }
                    break;

                default:
                    break;
            }
        }
        Conexao.enviar(clientSocket, str.toString());
    }

    public static void searchEmployee(int empNum, List<Employee> arrEmployees, boolean found, Socket clientSocket)
            throws IOException {
        empNum = Conexao.receberInt(clientSocket);
        for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext();) {
            Employee e = i.next();
            if (e.getEmpID() == empNum) {
                Conexao.enviar(clientSocket, e.toString());
                found = true;
            }
        }
    }

    public static void deleteEmployee(int empNum, List<Employee> arrEmployees, boolean found, Socket clientSocket)
            throws IOException {
        empNum = Conexao.receberInt(clientSocket);
        for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext();) {
            Employee e = i.next();
            if (e.getEmpID() == empNum) {
                i.remove();
                found = true;
            }
        }
        if (!found) {
            Conexao.enviar(clientSocket, "\nFuncionário não encontrado");
        } else {
            Conexao.enviar(clientSocket, "\nFuncionário deletado com sucesso");
        }
    }

    public static void updateEmployee(int empNum, List<Employee> arrEmployees, boolean found, Socket clientSocket)
            throws IOException {
        empNum = Conexao.receberInt(clientSocket);
        for (ListIterator<Employee> li = arrEmployees.listIterator(); li.hasNext();) {
            Employee e = li.next();
            if (e.getEmpID() == empNum) {
                li.set(new Employee(empNum, Conexao.receber(clientSocket), Conexao.receberFloat(clientSocket)));
                found = true;
            }
        }
        if (!found) {
            Conexao.enviar(clientSocket, "Funcionário não encontrado");
        } else {
            Conexao.enviar(clientSocket, "Funcionário atualizado com sucesso");
        }
    }
}
