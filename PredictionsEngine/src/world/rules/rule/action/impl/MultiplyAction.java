package world.rules.rule.action.impl;

import world.entities.EntitiesDefinition;
import world.entities.entity.EntityInstance;
import world.rules.rule.action.api.CalculationAction;

public class MultiplyAction extends CalculationAction {

    public MultiplyAction(EntitiesDefinition entitiesDefinition, String entityName, String resultProp, String s1, String s2) {
        super(entitiesDefinition, entityName, resultProp, s1, s2);
    }

    @Override
    public void invoke(EntityInstance entityInstance) {

        Double num1 = (Double) evaluateExpression(args1, entityInstance);
        Double num2 = (Double) evaluateExpression(args2, entityInstance);
        double newValue = num1 * num2;
        if (entityInstance.getProperty(resProp).getRange().getTo().doubleValue() > newValue)
            entityInstance.getProperty(resProp).setValue(num1 * num2);
        else {
            entityInstance.getProperty(resProp).setValue(entityInstance.getProperty(resProp).getRange().getTo().doubleValue());
        }
    }

    @Override
    public String toString() {
        return "MultiplyAction{" +
                "entityName='" + entityName + '\'' +
                ", resProp='" + resProp + '\'' +
                ", args1='" + args1 + '\'' +
                ", args2='" + args2 + '\'' +
                '}';
    }
}
