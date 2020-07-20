package com.br.transporteapi.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/*
  Essa classe serve para serializar um objeto do tipo List, basicamente ela inclui o nome do tipo de recurso
  solicitado em plural como um wrapper, exemplo:

  linhas {
  {
    "nomeDaLinha": "00-01"
  },
  {
    "nomeDaLinha": "00-02"
  },
  {
    "nomeDaLinha": "00-03"
  }
        }

  Acontece um problema quando essa classe é incluída, o swagger não consegue inferir a URL da documentação
  Procurei o porquê isso acontece e não achei em lugar algum.
  Porém voce pode usa-la fazendo requisições via Postman, basta descomentar a anottation abaixo
 */

// @JsonComponent
public class JsonListSerializer extends JsonSerializer<List> {

    @Override
    public void serialize(List list, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();

        ObjectMapper om = new ObjectMapper()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        if (list != null && !list.isEmpty())
            jsonGen.writeFieldName(new JsonReflectUtil(list.get(0).getClass()).convertToModelName().get());

        jsonGen.writeRawValue(om.writerWithView(serializerProvider.getActiveView())
                .writeValueAsString(list));


        jsonGen.writeEndObject();

    }
}
