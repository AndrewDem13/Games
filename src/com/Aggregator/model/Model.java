package Aggregator.model;


import Aggregator.view.View;
import Aggregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers)
    {
        if (view == null || providers == null || providers.length == 0) {
            throw  new IllegalArgumentException("Illegal arguments");
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers)
        {
            List<Vacancy> currentVac = provider.getJavaVacancies(city);
            for (Vacancy vacancy : currentVac)
                vacancies.add(vacancy);
        }
        view.update(vacancies);
    }
}
