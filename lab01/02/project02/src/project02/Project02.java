/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project02;

/**
 *
 * @author EMerckx
 */
public class Project02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a service
        DictService dictService = new DictService();
        
        // create a soap
        DictServiceSoap dictServiceSoap = dictService.getDictServiceSoap();
        
        // set the given word
        String given_word = "servizes";
        
        // get the corrections of the given word
        ArrayOfDictionaryWord arr = dictServiceSoap.match(given_word, "lev");
        
        // handle the output
        System.out.println("word: servizes");
        System.out.print("corrections: ");
        for(int i=0; i<arr.getDictionaryWord().size(); i++){
            System.out.print(arr.getDictionaryWord().get(i).word);
        }
        System.out.println(" ");
    }
    
}
