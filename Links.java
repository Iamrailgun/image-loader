import java.util.LinkedList;

public class Links {

    private LinkedList<String> linksList = new LinkedList<>();

    public String getLink(int e){
        return linksList.get(e);
    }

    public void addLink(String element){
        linksList.addLast(element);
    }

    public int getSize(){
        return linksList.size();
    }

    public void clear(){
        linksList.clear();
    }

    private LinkedList<String> linksListForLoad = new LinkedList<>();

    public void addLFL(String element){
        linksListForLoad.addLast(element);
    }

    public int getSizeLFL(){
        return linksListForLoad.size();
    }

    public String getLFL(int e){
        return linksListForLoad.get(e);
    }

    public void clearLFL(){
        linksListForLoad.clear();
    }

    private LinkedList<String> foundLinksList = new LinkedList<>();

    public String getFLLink(int e){
        return foundLinksList.get(e);
    }

    public void addFLLink(String element){
        foundLinksList.addLast(element);
    }

    public int getFLSize(){
        return foundLinksList.size();
    }

    public void clearFL(){
        foundLinksList.clear();
    }

    public void clearAll(){
        clear();
        clearFL();
        clearLFL();
    }
}
