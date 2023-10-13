package br.com.bluesburger.orderingsystem.core.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class DishDto implements Serializable {

	private static final long serialVersionUID = -2380385291965737284L;

	@NonNull
	private Long id;
	
	private LocalDateTime createdTime;
	
	private LocalDateTime updatedTime;
	
	private String name;
}
