import java.awt.*;
import java.awt.datatransfer.*;
  
class BoardListener extends Thread implements ClipboardOwner {
  Clipboard sClip = Toolkit.getDefaultToolkit().getSystemClipboard();
  
  public void run() {
    Transferable trans = sClip.getContents(this);
    regainOwnership(trans);
    System.out.println("Listening to board...");
    while(true) {}
  }
  
  public void lostOwnership(Clipboard c, Transferable t) {
    try
    {
      this.sleep(200);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    Transferable contents = sClip.getContents(this); //EXCEPTION
    processContents(contents);
    regainOwnership(contents);
  }
  
  void processContents(Transferable t) {
    System.out.println("Processing: " + t);
    try
    {
    Transferable clipdata=sClip.getContents(this);
    String what;
    what=(String)(clipdata.getTransferData(DataFlavor.stringFlavor));
    System.out.println("String: "+what);
  }
  catch(Exception ee){
   ee.printStackTrace();
  }
  }
  
  void regainOwnership(Transferable t) {
    sClip.setContents(t, this);
  }
  
  public static void main(String[] args) {
    BoardListener b = new BoardListener();
    b.start();
  }
}