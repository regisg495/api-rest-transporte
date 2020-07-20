package com.br.transporteapi.configuration.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams
        ({
                @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                        value = "Específica o número da página que você deseja", defaultValue = "0"),
                @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                        value = "Específica o número de registros em cada página", defaultValue = "10")
        })
public @interface ApiPageableDefaultFields {

}
