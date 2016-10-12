package com.javarush.test.level28.lesson15.big01;


import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

public class Aggregator
{
    public static void main(String[] args)
    {
        HtmlView view = new HtmlView();

        Provider[] providers = new Provider[10];

        Provider provider = new Provider(new HHStrategy());
        providers[0] = provider;

        Model model = new Model(view, providers);

        Controller controller = new Controller(model);

        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
