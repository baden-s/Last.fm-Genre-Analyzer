import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
public class lastfm {
    public static String[] lastfmRun(String USERNAME) {

        final String url = "https://www.last.fm/user/" + USERNAME + "/listening-report";

        try {
            final Document document = Jsoup.connect(url).get();

            Elements DataFile = document.getElementsByTag("b");
            String NameData = DataFile.html();
            String[] ArtistData = new String[5];

            //Getting first artist (stored separate from others)
            Elements TopArtist = document.select("h3.listening-report-top-item-heading");
            String MainArtist = TopArtist.html();
            String FinalArtist = "";
            int j = 0;
            while (MainArtist.charAt(j) != '\n') {
                FinalArtist += MainArtist.charAt(j);
                j++;
            }
            ArtistData[0] = FinalArtist;

            //Getting other four artists
            int counter = 1;
            String CName = "";
            int i = 0;
            while (counter <= 4) {
                CName += NameData.charAt(i);
                if (counter <= 4) {
                    if (NameData.charAt(i) == '\n') {
                        CName = CName.replace("\n", "");
                        ArtistData[counter] = CName;
                        CName = "";
                        counter++;
                    }
                }
                i++;
            }
            return ArtistData;

        } catch (HttpStatusException e) {
            String[] artist = {null};
            return artist;

        } catch (StringIndexOutOfBoundsException e){
            String[] artist = {null};
            return artist;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}