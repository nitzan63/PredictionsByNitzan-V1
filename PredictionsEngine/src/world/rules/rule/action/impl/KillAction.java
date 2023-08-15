package world.rules.rule.action.impl;

import world.entities.EntitiesDefinition;
import world.entities.entity.EntityInstance;
import world.rules.rule.action.api.AbstractAction;
import world.rules.rule.action.api.ActionType;

public class KillAction extends AbstractAction {
    public KillAction(EntitiesDefinition entitiesDefinition, String entityName) {
        super(ActionType.KILL, entitiesDefinition, entityName);
    }

    @Override
    public void invoke(EntityInstance entityInstance) {
        entitiesDefinition.removeEntityInstance(entityInstance.getSerialNumber());
        System.out.println("\nKilled Entity Number " + entityInstance.getSerialNumber());
        entitiesDefinition.setPopulation(entitiesDefinition.getPopulation() - 1);
        //TODO handle what happens if not found?
    }

    @Override
    public String getPropertyName() {
        return null;
    }

    @Override
    public String getByExpression() {
        return null;
    }


}
