package com.orange.xtreme.model;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.util.Log;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

public class Runner implements Runnable{
    private String login;
    private String psw;
    private URL metafeedUrl;
    private SpreadsheetService service;
    private List<SpreadsheetEntry> users;
    private SpreadsheetEntry user;
    /**
     *
     * @throws Exception
     */
    public void setup() throws Exception{
        login = "xtremefinanceorange";
        psw = "2340test";
        service = new SpreadsheetService("My Application");
        service.setUserCredentials(login,psw);
        metafeedUrl = new URL("http://spreadsheets.google.com/feeds/spreadsheets/private/full");
        users = service.getFeed(metafeedUrl, SpreadsheetFeed.class).getEntries();
    }
    public List<List<String>> getTransactions(String name) throws Exception{
        List<List<String>> trans = new ArrayList<List<String>>();
        WorksheetEntry account = new WorksheetEntry();
        for(int i = 1; i<user.getWorksheets().size(); i++){
            if(name.equals(user.getWorksheets().get(i).getTitle().getPlainText())){
                account = user.getWorksheets().get(i);
            }
        }
        int row = account.getRowCount()-1;
        URL cellFeedUrl = new URI(account.getCellFeedUrl().toString()
                + "?min-row=2&max-row="+row+"&min-col=1&max-col=1").toURL();
        List<CellEntry> change = service.getFeed(cellFeedUrl, CellFeed.class).getEntries();
        cellFeedUrl = new URI(account.getCellFeedUrl().toString()
                + "?min-row=2&max-row="+row+"&min-col=2&max-col=2").toURL();
        List<CellEntry> balance = service.getFeed(cellFeedUrl, CellFeed.class).getEntries();
        cellFeedUrl = new URI(account.getCellFeedUrl().toString()
                + "?min-row=2&max-row="+row+"&min-col=3&max-col=3").toURL();
        List<CellEntry> date = service.getFeed(cellFeedUrl, CellFeed.class).getEntries();
        cellFeedUrl = new URI(account.getCellFeedUrl().toString()
                + "?min-row=2&max-row="+row+"&min-col=4&max-col=4").toURL();
        List<CellEntry> cood = service.getFeed(cellFeedUrl, CellFeed.class).getEntries();
        for (int i = 0; i<change.size(); i++) {
            trans.add(new ArrayList<String>());
            trans.get(i).add(change.get(i).getCell().getValue());
            trans.get(i).add(balance.get(i).getCell().getValue());
            trans.get(i).add(date.get(i).getCell().getValue());
            trans.get(i).add(cood.get(i).getCell().getValue());
        }
        return trans;
    }
    /**
     *
     * @return
     * @throws Exception
     */
    public List<List<String>> getAccounts() throws Exception{
        List<List<String>> accounts = new ArrayList<List<String>>();
        List<WorksheetEntry> list = user.getWorksheets();
        for(int i = 1; i<list.size(); i++){
            accounts.add(new ArrayList<String>());
            accounts.get(i-1).add((list.get(i).getTitle().getPlainText()));
            URL cellFeedUrl = new URI(list.get(i).getCellFeedUrl().toString()
                    + "?min-row=2&max-row=2&min-col=3&max-col=3").toURL();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            accounts.get(i-1).add(cellFeed.getEntries().get(0).getCell().getValue());
            int row = list.get(i).getRowCount()-1;
            cellFeedUrl = new URI(list.get(i).getCellFeedUrl().toString()
                    + "?min-row=" + row +"&max-row=" + row +"&min-col=2&max-col=3").toURL();
            cellFeed =service.getFeed(cellFeedUrl, CellFeed.class);
            accounts.get(i-1).add(cellFeed.getEntries().get(0).getCell().getValue());
            accounts.get(i-1).add(cellFeed.getEntries().get(1).getCell().getValue());
        }
        return accounts;
    }
    /**
     *
     * @param name
     * @param change
     * @param balance
     * @param date
     * @throws IOException
     * @throws ServiceException
     */
    public void addTransaction(String name, String change, String balance, String date, String cood) throws IOException, ServiceException{
        WorksheetEntry account = new WorksheetEntry();
        for(int i = 1; i<user.getWorksheets().size(); i++){
            if(name.equals(user.getWorksheets().get(i).getTitle().getPlainText())){
                account = user.getWorksheets().get(i);
            }
        }
        ListEntry row = new ListEntry();
        URL listFeedUrl = account.getListFeedUrl();
        row.getCustomElements().setValueLocal("change", change);
        row.getCustomElements().setValueLocal("balance", balance);
        row.getCustomElements().setValueLocal("date", date);
        row.getCustomElements().setValueLocal("cood", date);
        row = service.insert(listFeedUrl, row);
        account = account.getSelf();
        account.setRowCount(account.getRowCount()+1);
        account.update();
    }
    /**
     *
     * @param name
     * @param balance
     * @param date
     * @return
     * @throws Exception
     */
    public boolean addAccount(String name, String balance, String date, String cood) throws Exception{
        for(int i = 1; i<user.getWorksheets().size(); i++){
            if(name.equals(user.getWorksheets().get(i).getTitle().getPlainText())){return false;}
        }
        WorksheetEntry account = new WorksheetEntry();
        account.setTitle(new PlainTextConstruct(name));
        account.setColCount(4);
        account.setRowCount(3);
        URL worksheetFeedUrl = user.getWorksheetFeedUrl();
        service.insert(worksheetFeedUrl, account);
        for(int i = 1; i<user.getWorksheets().size(); i++){
            if(name.equals(user.getWorksheets().get(i).getTitle().getPlainText())){
                account = user.getWorksheets().get(i);
            }
        }
        URL cellFeedUrl = account.getCellFeedUrl();
        CellEntry newEntry = new CellEntry(1,1,"Change");
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 2, "Balance");
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 3, "Date");
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 4, "Cood");
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(2, 1, "0");
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(2, 2, balance);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(2, 3, date);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(2, 3, cood);
        service.insert(cellFeedUrl, newEntry);
        return true;
    }
    /**
     *
     * @param username
     * @param password
     * @param date
     * @param email
     * @return
     * @throws Exception
     */
    public boolean createUser(String username, String password, String date, String email, String hint, String answer) throws Exception{
        for(SpreadsheetEntry usr : users){
            if(username.equals(usr.getTitle().getPlainText())){return false;}
        }
        DocsService docsService = new DocsService("MySampleApplication-v3");
        docsService.setUserCredentials(login, psw);
        URL GOOGLE_DRIVE_FEED_URL = new URL("https://docs.google.com/feeds/default/private/full/");
        com.google.gdata.data.docs.SpreadsheetEntry documentListEntry = new com.google.gdata.data.docs.SpreadsheetEntry();
        documentListEntry.setTitle(new PlainTextConstruct(username));
        documentListEntry = docsService.insert(GOOGLE_DRIVE_FEED_URL, documentListEntry);
        SpreadsheetEntry temp = new SpreadsheetEntry();
        users = service.getFeed(metafeedUrl, SpreadsheetFeed.class).getEntries();
        for(SpreadsheetEntry usr : users){
            if(username.equals(usr.getTitle().getPlainText())){
                temp = usr;
            }
        }
        WorksheetEntry general = temp.getWorksheets().get(0);
        //Log.i("System.out", general.getTitle().getPlainText());
        general.setTitle(new PlainTextConstruct("General"));
        general.setColCount(5);
        general.setRowCount(1);
        general.update();
        //Log.i("System.out", general.getTitle().getPlainText());
        URL cellFeedUrl = general.getCellFeedUrl();
        CellEntry newEntry = new CellEntry(1, 1, password);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 2, email);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 3, date);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 4, hint);
        service.insert(cellFeedUrl, newEntry);
        newEntry = new CellEntry(1, 5, answer);
        service.insert(cellFeedUrl, newEntry);
        return true;
    }
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public boolean login(String username, String password) throws Exception{
        for(SpreadsheetEntry usr : users){
            if(username.equals(usr.getTitle().getPlainText())){
                WorksheetEntry general = usr.getWorksheets().get(0);
                URL cellFeedUrl = new URI(general.getCellFeedUrl().toString()
                        + "?min-row=1&max-row=1&min-col=1&max-col=1").toURL();
                CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
                for (CellEntry cell : cellFeed.getEntries()) {
                    if(cell.getCell().getValue().equals(password))	{
                        user = usr;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
    public List<String> getPass(String username) throws Exception{
    	List<String> trans = new ArrayList<String>();
        for(SpreadsheetEntry usr : users){
            if(username.equals(usr.getTitle().getPlainText())){
                WorksheetEntry general = usr.getWorksheets().get(0);
                URL cellFeedUrl = new URI(general.getCellFeedUrl().toString()
                        + "?min-row=1&max-row=1&min-col=1&max-col=2").toURL();
                CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
                for (CellEntry cell : cellFeed.getEntries()) {
                	trans.add(cell.getCell().getValue());
                }
                logout();
                return trans;
            }
        }
        trans.add("notfound");
        logout();
        return null;
    }
    public void resetPass(String username, String password) throws Exception{
        for(SpreadsheetEntry usr : users){
            if(username.equals(usr.getTitle().getPlainText())){
                WorksheetEntry general = usr.getWorksheets().get(0);
                URL cellFeedUrl = general.getCellFeedUrl();
                CellEntry newEntry = new CellEntry(1, 1, password);
                service.insert(cellFeedUrl, newEntry);
                logout();
                return;
            }
        }
        logout();
        return;
    }
    public void logout() throws Exception{
        user = null;
        users = null;
    }
    // call login first
    public boolean deleteAccount(String account) throws Exception{
    	List<WorksheetEntry> worksheets = user.getWorksheets();
        for(int i = 1; i<worksheets.size(); i++){
            if(account.equals(worksheets.get(i).getTitle().getPlainText())){
            	worksheets.get(i).delete();	
            	return false;
            }
        }
        return false;
    }
    public List<List<String>> getUsers() throws Exception{
        List<List<String>> accounts = new ArrayList<List<String>>();
        int i = 0;
        for(SpreadsheetEntry usr : users){
        	accounts.add(new ArrayList<String>());
        	accounts.get(i).add(usr.getTitle().getPlainText());
        	WorksheetEntry general = usr.getWorksheets().get(0);
        	URL cellFeedUrl = new URI(general.getCellFeedUrl().toString()
                    + "?min-row=1&max-row=1&min-col=1&max-col=5").toURL();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            for (CellEntry cell : cellFeed.getEntries()) {
            	accounts.get(i).add(cell.getCell().getValue());
            }
            i++;
        }
        logout();
        return accounts;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
