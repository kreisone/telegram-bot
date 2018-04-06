
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class MyTelegramBot extends TelegramLongPollingBot {
    
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId());
            
            String word = update.getMessage().getText();
            
            Main main = new Main();
            
            String trans_word = main.getTranslation(word);
            
            
            
            message.setText(word + " - " + trans_word);
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "NameBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "3252365:AAFWK323623y234yRalkQbJzo54u4534dXQH5R_4";
    }
}
