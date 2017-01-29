package projeto.utils.builders;

import java.util.Vector;

import projeto.models.FolhetoInformativo;

/**
 * Created by crist on 04/01/2017.
 */

public class FIBuilder {

    public FolhetoInformativo getFromText(StringBuilder document){


        return new FolhetoInformativo(getTitles(document),getSections(document));
    }

    private String[] getTitles(StringBuilder document){

        Vector<String> aux = new Vector<String>();
        int start = 0, end = 0, next = 0;
        start = document.indexOf("<menu>");
        end = document.lastIndexOf("</menu>");
        while((next = document.indexOf("</menu>", start)) <= end && (next > 0) && (start >= 0)){
            aux.add(document.substring(start + "<menu>".length(),next));
            start = document.indexOf("<menu>",next);
        }

        String[] menu = new String[aux.size()];
        for(int i = 0; i < aux.size(); i++)
            menu[i] = aux.get(i);

        return menu;
    }

    private static String[] getSections(StringBuilder document){

        Vector<String> aux = new Vector<String>();
        int start = 0, end = 0, next = 0;
        start = document.indexOf("<text>");
        end = document.lastIndexOf("</text>");
        while((next = document.indexOf("</text>", start)) <= end && (next > 0) && (start >= 0)){
            aux.add(document.substring(start + "<text>".length(),next));
            start = document.indexOf("<text>",next);
        }

        String[] sections = new String[aux.size()];
        for(int i = 0; i < aux.size(); i++)
            sections[i] = aux.get(i);

        return sections;
    }

}
