package SIH;

import org.bson.Document;
import org.jasypt.util.text.StrongTextEncryptor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class PassFunc {
	MongoClientURI uri  = new MongoClientURI("mongodb://sjsidjain:sjsidjain@ds145359.mlab.com:45359/testhack"); 
    MongoClient client = new MongoClient(uri);
    MongoDatabase db = client.getDatabase(uri.getDatabase());
    MongoCollection <Document> tc = db.getCollection("testcol");
    StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
    Document doc;
    
    PassFunc(){
    	textEncryptor.setPassword("mycode");
    }
    public Document getDocument(String id)
    {
    	FindIterable<Document> doc = tc.find(eq("username",id));
        for(Document d : doc)
	        this.doc=d;
        
    	return this.doc;
    }
  public boolean updatePass(String user,String pass)
  {
	  String encryptedPassword = textEncryptor.encrypt(pass);
  	tc.updateOne(eq("username",user),new Document("$set", new Document("password",encryptedPassword)));
    return true; 
  }
  
  public boolean checkPass(String pass){
	  String spass = textEncryptor.decrypt(doc.getString("password"));
	  if(pass.equals(spass)){
		  return true;
	  }
	  else
		  return false;
  }
  
  

}
