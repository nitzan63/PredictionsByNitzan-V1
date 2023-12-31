package world.rules.rule.action.impl;

import world.entities.EntitiesDefinition;
import world.entities.entity.EntityInstance;
import world.rules.rule.action.api.AbstractAction;
import world.rules.rule.action.api.ActionType;

public class SetAction extends AbstractAction {
    private final String propertyName;
    private final String expression;

    public SetAction(EntitiesDefinition entitiesDefinition, String propertyName, String expression, String entityName) {
        super(ActionType.SET, entitiesDefinition, entityName);
        this.propertyName = propertyName;
        this.expression = expression;
    }

    @Override
    public void invoke(EntityInstance entityInstance) {
        Object value = evaluateExpression(expression, entityInstance);
        if (value instanceof Double){ // handle the cases when trying to set value out of range.
            if ((double) value < entityInstance.getProperty(propertyName).getRange().getFrom().doubleValue()){
                entityInstance.getProperty(propertyName).setValue(entityInstance.getProperty(propertyName).getRange().getFrom().doubleValue());
            } else if ((double) value > entityInstance.getProperty(propertyName).getRange().getTo().doubleValue()){
                entityInstance.getProperty(propertyName).setValue(entityInstance.getProperty(propertyName).getRange().getTo());
            } else entityInstance.getProperty(propertyName).setValue(value);
        } else {
            entityInstance.getProperty(propertyName).setValue(value);
        }
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String getByExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "SetAction{" +
                "propertyName='" + propertyName + '\'' +
                ", expression='" + expression + '\'' +
                ", entityName='" + entityName + '\'' +
                '}';
    }
}
