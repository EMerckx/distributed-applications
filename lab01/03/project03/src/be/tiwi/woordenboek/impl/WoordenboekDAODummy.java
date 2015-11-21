/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.tiwi.woordenboek.impl;

import be.tiwi.woordenboek.data.Woordenboek;
import be.tiwi.woordenboek.data.WoordenboekDAO;
import com.aonaware.services.webservices.ArrayOfDictionary;
import com.aonaware.services.webservices.ArrayOfDictionaryWord;
import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.DictService;
import com.aonaware.services.webservices.DictServiceSoap;
import com.aonaware.services.webservices.Dictionary;
import com.aonaware.services.webservices.DictionaryWord;
import com.aonaware.services.webservices.WordDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vongenae
 */
public class WoordenboekDAODummy implements WoordenboekDAO {

    List<Woordenboek> woordenboeken;
    Random random = new Random();

    DictService dictService;
    DictServiceSoap dictServiceSoap;

    public WoordenboekDAODummy() {
        this.dictService = new DictService();
        this.dictServiceSoap = dictService.getDictServiceSoap();

        this.woordenboeken = initWoordenboeken();
    }

    @Override
    public List<Woordenboek> getWoordenboeken() {
        return woordenboeken;
    }

    private List<Woordenboek> initWoordenboeken() {

        // get all the dictionaries
        ArrayOfDictionary arrayOfDictionary = dictServiceSoap.dictionaryList();
        List<Dictionary> dictionarys = arrayOfDictionary.getDictionary();

        // map the dictionaries on a list of Woordenboek
        List<Woordenboek> lijstWoordenboeken = new ArrayList<>();
        for (int i = 0; i < dictionarys.size(); i++) {
            String id = dictionarys.get(i).getId();
            String name = dictionarys.get(i).getName();
            lijstWoordenboeken.add(new Woordenboek(id, name));
        }
        return lijstWoordenboeken;
    }

    @Override
    public List<String> zoekWoorden(String prefix, String woordenboekId) {

        // get the full words of the given prefix in the given dictionary
        ArrayOfDictionaryWord arrayOfDictionaryWord
                = dictServiceSoap.matchInDict(woordenboekId, prefix, "prefix");
        List<DictionaryWord> dictionaryWords
                = arrayOfDictionaryWord.getDictionaryWord();

        // map the words on a list of String
        List<String> woorden = new ArrayList<>();
        for (int i = 0; i < dictionaryWords.size(); i++) {
            woorden.add(dictionaryWords.get(i).getWord());
        }
        return woorden;
    }

    @Override
    public List<String> getDefinities(String woord, String woordenboekId) {

        // get the definition of the given word in the given dictionary
        WordDefinition wordDefinition
                = dictServiceSoap.defineInDict(woordenboekId, woord);
        List<Definition> definitions
                = wordDefinition.getDefinitions().getDefinition();

        // map the definitions on a list of String
        List<String> definities = new ArrayList<>();
        for (int i = 0; i < definitions.size(); i++) {
            definities.add(definitions.get(i).getWordDefinition());
        }
        return definities;
    }
}
