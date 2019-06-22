package com.wind.common.utils.mbplus;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JsonObjectMapper() {
		super();
		// this.enable(SerializationFeature.INDENT_OUTPUT);//设置输出换行
		this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.getSerializationConfig().withSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		this.setVisibility(PropertyAccessor.GETTER, Visibility.PUBLIC_ONLY);
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//configure(SerializationConfig.Feature. INDENT_OUTPUT , Boolean. TRUE );
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override  
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
            	 jg.writeString("");
            }  
        });
        
  
	}

}
