package Aggregator;

import Aggregator.model.Model;

public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if (model == null)
            throw new IllegalArgumentException("Illegal arguments at Controller");
        else
            this.model = model;
    }

    public void onCitySelect(String cityName)
    {
        model.selectCity(cityName);
    }
}
