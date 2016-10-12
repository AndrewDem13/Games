package Aggregator;


import Aggregator.model.HHStrategy;
import Aggregator.model.Model;
import Aggregator.model.Provider;
import Aggregator.view.HtmlView;

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
