/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testequationservice;

import java.util.List;

/**
 *
 * @author EMerckx
 */
public class TestEquationService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Double> list = solveQuadratic(1, -5, 6);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i) + " ");
        }
    }

    private static java.util.List<java.lang.Double> solveQuadratic(int a, int b, int c) {
        ws.EquationService_Service service = new ws.EquationService_Service();
        ws.EquationService port = service.getEquationServicePort();
        return port.solveQuadratic(a, b, c);
    }

}
