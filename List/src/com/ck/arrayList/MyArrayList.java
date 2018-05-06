package com.ck.arrayList;

import java.util.ArrayList;

/**
 * 
 *<p>Title:MyArrayList</p>
 *<p>Description:�Զ���ArrayList</p>
 *<p>Company:cks</p>	
 * @author:ck
 * @date:2018-5-4
 */
public class MyArrayList <T>{
	//�洢���ݵ��������
	private Object[] arrayData;
	//����
	int capcity=0;
	//�������Ѿ��洢�����ݵ���Ŀ
	private int size=0;
	//�������������Ĺ��췽ʽ
	public MyArrayList(int capcity){
		if(capcity<0){
			throw new RuntimeException("ArrayList������������Ϊ����");
		}
		arrayData=new Object[capcity];
		this.capcity=capcity;
	}
	//�޲ι��췽��
	public MyArrayList(){
		
		this(2);
	}
	
	//����������Ԫ�صķ���
	public <T>boolean add(T t){
		
		if(size>=this.capcity){
			int newSize=(size*3)<<2;
			Object[] tempData= new Object[newSize] ;
			System.arraycopy(arrayData, 0, tempData, 0, size);
			arrayData=tempData;	
		} 
		arrayData[size]=t;
		size++;
		return true;
	}
	//ɾ��������ָ��Ԫ�ء�
	//ArrayList
	public <T> boolean  remove(T t){
		int index= indexOf(t);
		if(index!=-1){
			System.arraycopy(arrayData, index+1, arrayData, index, size-index-1);
			arrayData[--size]=null;
			return true;
		}
		return false;
		/*int len =arrayData.length;
		if(t==null){//�ж�ɾ����Ԫ��
			for (int i = 0; i < len; i++) {
				if(arrayData[i]==null){
					System.arraycopy(arrayData, i+1, arrayData, i, len-i-1);
					arrayData[size--]=null;
					
					return true;
				}
			}
		}	else{//ɾ��Ԫ�ز�Ϊ��
			for (int i = 0; i < arrayData.length; i++) {
				if(arrayData[i]!=null){
					if(arrayData[i].equals(t)){
						System.arraycopy(arrayData, i+1, arrayData, i, len-i-1);
						arrayData[size--]=null;
						return true;
					}
					
				}
			}
		}
		return false;
		*/

		
		
	}
	//���������Ĵ�С
	public int size(){
		return size;
	}
	//�ж������Ƿ�Ϊ��
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	//������������е�Ԫ��
	public void clear(){
		size=0;
		for (int i = 0; i < size; i++) {
			arrayData[i]=null;
		}
	}
	//����Ԫ���������е�����λ��,����˳��ʽѰ��
	public <T> int indexOf(T t){
		if(t==null){
			for (int i = 0; i < size; i++) {
				if(arrayData[i]==null){
					return i;
				}
			}
		} else{
			for (int i = 0; i < size; i++) {
				if(arrayData[i]!=null){
					if(arrayData[i].equals(t)){
						return i;
					}
				}
			}
		}
		return -1;
	}
	//����Ԫ���������е�����λ��,����˳��ʽѰ��
	public int lastIndexOf(T t){
		if(t==null){
			for (int i = size-1; i >= 0; i--) {
				if(arrayData[i]==null){
					return i;
				}
			}
		} else{
			for (int i = size-1; i>=0; i--) {
				if(arrayData[i]!=null){
					if(arrayData[i].equals(t)){
						return i;
					}
				}
			}
		}
		return -1;
	}
	//������������ֵ���õ�������Ӧ��Ԫ��ֵ
	public T get(int index){
		checkIndexValid(index);
		return (T) arrayData[index];
	}
	//���������ض�������λ�õ�Ԫ��ֵ���޸�Ϊָ����ֵ
	public <T>T set(int index,T t){
		checkIndexValid(index);
		T oldValue=(T) arrayData[index];
		arrayData[index]=t;
		return oldValue;
	}
	//������������ֵ��ɾ����������Ӧ��Ԫ�ء�
	public <T> T remove(int index){
		checkIndexValid(index);
		T oldValue=(T) arrayData[index];
		System.arraycopy(arrayData, index+1, arrayData, index, size-index-1);
		arrayData[--size]=null;
		return oldValue;
	}
	//�������������ֵ����Ч��
	public boolean checkIndexValid(int index){
		if(index<0||index>=size){
			throw new RuntimeException("��������ֵ����Ϊ���������ܴ��ڵ����������洢Ԫ�ص���Ŀ");
		}
		return true;
	}

	
}
