package com.dwinq.nintendoRegistration.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	protected Long id;

	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(super.toString() + " : ");

		
		for(Method method : this.getClass().getDeclaredMethods()){
			try {
				String getterMethodName = method.getName();
				if(getterMethodName.startsWith("get") && !method.getReturnType().equals(List.class)){
					stringBuffer.append(getterMethodName + "() = " + method.invoke(this, null) + "\n");
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return stringBuffer.toString();
	}
}
