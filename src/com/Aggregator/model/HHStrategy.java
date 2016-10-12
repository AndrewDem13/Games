package Aggregator.model;


import Aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy
{
    private final static String URL_FORMAT = "https://hh.ua/search/vacancy?text=java&area=115&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> result = new ArrayList<>();
        Document document;
        int page = 0;
        try
        {
            while (true)
            {
                document = getDocument(page++);
                if (document == null) break;
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;
                for (Element element : elements)
                {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setSiteName("http://hh.ua");

                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null)
                    {
                        salary = salaryElement.text();
                    }
                    vacancy.setSalary(salary);

                    result.add(vacancy);
                }
            }
        }
        catch (IOException ex) {}
        return result;
    }

    protected Document getDocument(int page) throws IOException
    {
        String url = String.format(URL_FORMAT, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")
                .get();
        return document;
    }
}
