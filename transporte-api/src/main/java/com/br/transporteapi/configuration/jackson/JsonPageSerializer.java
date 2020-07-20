package com.br.transporteapi.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class JsonPageSerializer extends JsonSerializer<Page> {

    @Override
    public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {

        ObjectMapper om = new ObjectMapper()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        jsonGen.writeStartObject();
        jsonGen.writeFieldName("numeroDeRegistrosDaPagina");
        jsonGen.writeNumber(page.getSize());
        jsonGen.writeFieldName("numeroDaPagina");
        jsonGen.writeNumber(page.getNumber());
        jsonGen.writeFieldName("totalDeElementos");
        jsonGen.writeNumber(page.getTotalElements());
        jsonGen.writeFieldName("ultima");
        jsonGen.writeBoolean(page.isLast());
        jsonGen.writeFieldName("totalDePaginas");
        jsonGen.writeNumber(page.getTotalPages());
        jsonGen.writeObjectFieldStart("ordenacao");
        jsonGen.writeFieldName("ordenado");
        jsonGen.writeBoolean(page.getSort().isSorted());
        jsonGen.writeFieldName("naoOrdenado");
        jsonGen.writeBoolean(page.getSort().isUnsorted());
        jsonGen.writeFieldName("vazio");
        jsonGen.writeBoolean(page.getSort().isEmpty());
        jsonGen.writeEndObject();
        jsonGen.writeFieldName("primeira");
        jsonGen.writeBoolean(page.isFirst());
        jsonGen.writeFieldName("numeroDeElementos");
        jsonGen.writeNumber(page.getNumberOfElements());
        if (!page.getContent().isEmpty())
            jsonGen.writeFieldName(new JsonReflectUtil(page.getContent().get(0).getClass()).convertToModelName().get());

        jsonGen.writeRawValue(om.writerWithView(serializerProvider.getActiveView())
                .writeValueAsString(page.getContent()));

        jsonGen.writeEndObject();
    }

}

