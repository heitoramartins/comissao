package com.compra.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.compra.entity.Pedido;



public class VendaValidation implements ConstraintValidator<VendaConstraint, Pedido> {

    @Override
    public void initialize(VendaConstraint annotation) {}

    @Override
    public boolean isValid(Pedido pedido, ConstraintValidatorContext context) {

        return validateMap(pedido, context);
    }

   private boolean validateMap(Pedido pedido, ConstraintValidatorContext context) {
	   /* 
        boolean valid = true;

        if (map == null) {
            context.buildConstraintViolationWithTemplate("O mapa esta nulo. O corpo da requisicao deve ser preenchido.").addConstraintViolation();
            valid &= false;
        } else {

            if (map.getName() == null || map.getName().trim().isEmpty()) {
                context.buildConstraintViolationWithTemplate("O nome do mapa esta nulo.").addConstraintViolation();
                valid &= false;
            }

            if (map.getRoutes() == null || map.getRoutes().isEmpty()) {
                context.buildConstraintViolationWithTemplate("Nao ha nenhuma rota no mapa.").addConstraintViolation();
                valid &= false;
            }

            if (map.getRoutes() != null) {

                for (Route route : map.getRoutes()) {
                    valid &= validateRoute(route, context);
                }
            }
        }

        return valid;
    */
	   return false;
    	
    }

    /*private boolean validateRoute(Item route, ConstraintValidatorContext context) {

        boolean valid = true;

        if (route == null) {
            context.buildConstraintViolationWithTemplate("A rota esta nula.").addConstraintViolation();
            valid &= false;
        } else {

            if (route.getOrigin() == null) {
                context.buildConstraintViolationWithTemplate("A origem da rota nao foi definida.").addConstraintViolation();
                valid &= false;
            }

            if (route.getDestiny() == null) {
                context.buildConstraintViolationWithTemplate("O destino da rota nao foi definido.").addConstraintViolation();
                valid &= false;
            }

            valid &= validatePoint(route.getOrigin(), context);

            valid &= validatePoint(route.getDestiny(), context);
        }

        return valid;
    	
    }*/

   /* private boolean validatePoint(Point point, ConstraintValidatorContext context) {

        boolean valid = true;

        if (point == null) {
            context.buildConstraintViolationWithTemplate("O ponto esta nulo.").addConstraintViolation();
            valid &= false;
        } else {

            if (point.getName() == null || point.getName().trim().isEmpty()) {
                context.buildConstraintViolationWithTemplate("O nome do ponto esta nulo.").addConstraintViolation();
                valid &= false;
            }
        }

        return valid;
    	return false;
    }*/

}
