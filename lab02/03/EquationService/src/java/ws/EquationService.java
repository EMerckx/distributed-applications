/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author EMerckx
 */
@WebService(serviceName = "EquationService")
public class EquationService {

    @WebMethod(operationName = "solveQuadratic")
    public double[] solveQuadratic(@WebParam(name = "a") int a, @WebParam(name = "b") int b, @WebParam(name = "c") int c) {
        int d = b * b - 4 * a * c;

        double[] solutions;
        if (d < 0) {
            solutions = new double[0];
        } else if (d == 0) {
            solutions = new double[1];
            solutions[0] = -b / (2 * a);
        } else {
            solutions = new double[2];
            solutions[0] = (-b - Math.sqrt(d)) / (2 * a);
            solutions[1] = (-b + Math.sqrt(d)) / (2 * a);
        }
        return solutions;
    }
}
