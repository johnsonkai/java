package com.ck.arrayList;

import java.util.ArrayList;

/**
 * 
 *<p>Title:MyArrayList</p>
 *<p>Description:自定义ArrayList</p>
 *<p>Company:cks</p>	
 * @author:ck
 * @date:2018-5-4
 */
public class MyArrayList <T>{
	//存储数据的数组对象
	private Object[] arrayData;
	//容量
	int capcity=0;
	//容器中已经存储的数据的数目
	private int size=0;
	//设置容器容量的构造方式
	public MyArrayList(int capcity){
		if(capcity<0){
			throw new RuntimeException("ArrayList容器容量不能为负数");
		}
		arrayData=new Object[capcity];
		this.capcity=capcity;
	}
	//无参构造方法
	public MyArrayList(){
		
		this(2);
	}
	
	//往容器增加元素的方法
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
	//删除容器中指定元素。
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
		if(t==null){//判断删除空元素
			for (int i = 0; i < len; i++) {
				if(arrayData[i]==null){
					System.arraycopy(arrayData, i+1, arrayData, i, len-i-1);
					arrayData[size--]=null;
					
					return true;
				}
			}
		}	else{//删除元素不为空
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
	//计算容器的大小
	public int size(){
		return size;
	}
	//判断容器是否为空
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	//清空容器中所有的元素
	public void clear(){
		size=0;
		for (int i = 0; i < size; i++) {
			arrayData[i]=null;
		}
	}
	//计算元素在容器中的索引位置,按照顺序方式寻找
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
	//计算元素在容器中的索引位置,逆序顺序方式寻找
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
	//根据容器索引值，得到容器相应的元素值
	public T get(int index){
		checkIndexValid(index);
		return (T) arrayData[index];
	}
	//将容器中特定个索引位置的元素值，修改为指定的值
	public <T>T set(int index,T t){
		checkIndexValid(index);
		T oldValue=(T) arrayData[index];
		arrayData[index]=t;
		return oldValue;
	}
	//根据容器索引值，删除容器中相应的元素。
	public <T> T remove(int index){
		checkIndexValid(index);
		T oldValue=(T) arrayData[index];
		System.arraycopy(arrayData, index+1, arrayData, index, size-index-1);
		arrayData[--size]=null;
		return oldValue;
	}
	//检查容器的索引值的有效性
	public boolean checkIndexValid(int index){
		if(index<0||index>=size){
			throw new RuntimeException("容器索引值不能为负数，或不能大于等于容器所存储元素的数目");
		}
		return true;
	}

	
}
