package Aggregator.view;

import Aggregator.Controller;
import Aggregator.vo.Vacancy;

import java.util.List;

public interface View
{
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
