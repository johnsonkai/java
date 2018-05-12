package com.ck.linkedList;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 *<p>Title:MyArrayList</p>
 *<p>Description:自定义LinkedList</p>
 *<p>Company:cks</p>	
 * @author:ck
 * @date:2018-5-4
 */
public class MyLinkedList<T> {
	//MyLinkedList容器的大小
	private int size=0;
	//指定初始头节点
	Node<T> header;
	//初始化尾节点
	Node<T> tail ;
	//清空链表
	public MyLinkedList(){
		
	}
	//MyLinkedList容器的增加元素的方式,每次将节点插入到链表最后一个节点后面。
	/**
	 * 往容器中增加指定元素值，也就是在链表后面添加指定元素值的节点
	 * @param t
	 * @return 
	 */
	public  boolean add(T t){
		addLast(t);
		return true;
		
	}
	/**
	 * 在容器指定索引位置增添指定的元素值
	 * @param index
	 * @param t
	 */
	public void add(int index,T t){
		checkAddRange(index);
		if(index==size){//在容器最后添加节点
			add(t);
		}else{
			Node<T> next=getNode(index);
			Node<T> pre=next.pre;
			Node<T> addNode= new Node<T>(t, pre, next);	
			next.pre=addNode;
			if(pre==null){//在容器第一个位置新增节点,即是新增头结点
				header=pre;
			} else{//在容器中间新增节点。
				pre.next=addNode;
			}
			
		}
	}
	/**
	 * 将元素节点新增到容器中第一个节点,第一次增加的节点就是未节点。
	 * @param t
	 */
	public void addFirst(T t){
		Node<T> temp=header;
		Node<T> addNode= new Node<T>(t, null, temp);
		header=addNode;
		if(temp==null){//容器为空，将新增节点设置为未节点。
			tail=addNode;
		} else{//容器非空，将新增节点添加到第一个节点。
			temp.pre=addNode;
		}
		size++;
	}
	/**
	 * 将元素节点添加到容器的最后一个节点
	 * @param t
	 */
	public void addLast(T t){
		Node<T> tempNode=tail;
		Node<T> addNode = new Node<T>(t,tail,null);
		tail=addNode;
		if(tempNode==null){//容器没有节点元素，将新增节点元素设置为头节点
			header=addNode;
		}  else{//容器已有节点元素，将新增节点放在容器未节点后面。
			tempNode.next=addNode;
		}
		size++;
	}
	/**
	 * 获得容器的大小
	 * @return
	 */
	public int  size(){
		return size;
	}
	/**
	 * 删除容器中指定元素的值
	 * @param o
	 * @return
	 */
	public boolean remove(Object o){
		if(o==null){
			for (Node<T> j=header; j!=null; j=j.next) {
				if(j.element==null){
					removeNode(j);
					size--;
					return true;
				}
			}
		} else{
			for (Node<T> j=header; j!=null; j=j.next) {
				if(j.element!=null){
					removeNode(j);
					size--;
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 根据指定所指定的索引值删除容器相应的节点。
	 * @param index
	 * @return
	 */
	public T remove(int index ){
		checkRange(index);
		T removeElement =getNode(index).element;
		removeNode(getNode(index));
		return removeElement; 
		
	}
	/**
	 * 删除容器头元素节点
	 * @return
	 */
	public T removeFirst(){
		Node<T> temp=header;
		if(temp==null){
			throw new NoSuchElementException("容器没有存在此元素");
		}
		return removeNode(temp);
	}
	
	/**
	 * 删除容器未元素节点
	 * @return
	 */
	public T removeLast(){
		Node<T> temp=tail;
		if(temp==null){
			throw new NoSuchElementException("容器没有存在此元素");
		}
		return removeNode(temp);
	}
	
	/**
	 * 根据容器索引值获取相应元素值
	 * @param index
	 * @return
	 */
	public T get(int index){
		return getNode(index).element;
	}
	/**
	 * 
	 * 获取容器中第一个节点的元素值
	 * @return
	 */
	public T getFirst(){
		Node<T> first=header;
		if(first==null){
			throw new NoSuchElementException("该容器不存在元素值");
		}
		return header.element;
	}
	/**
	 * 获取容器中最后一个节点的元素值
	 */
	public T getLast(){
		Node<T> last=tail;
		if(last==null){
			throw new NoSuchElementException("该容器不存在元素值");
		}
		return tail.element;
	}
	/**
	 * 判断容器是否为空
	 * @return
	 */
	public boolean isEmpty(){
		if(size==0){
			return true;
		} else{
			return false;
		}
		
		
	}
	/**
	 * 清空容器,把所有的节点清空
	 */
	public void clear(){
		for(Node<T> node=header;node!=null;){
			Node<T> next =node.next;
			node.element=null;
			node.pre=null;
			node.next=null;
			node=next;
		}
		size=0;
	}
	
	/**
	 * 根据索引值获取相应节点
	 * @param index
	 * @return
	 */
	private Node<T>  getNode(int index){
		
		if(index<(size>>1)){
			Node<T> temp =header;
			for(int i=0;i<index;i++){
				temp=temp.next;
				
			}
			return temp;
		} else{
			Node<T> temp =tail;
			for(int i=size-1;i>index;i--){
				temp=temp.pre;
				
			}
			return temp;
		}
		
	}
	/**
	 * 验证容器已经存在元素的索引值的范围
	 * @param index
	 */
	private void checkRange(int index){
		if(index<0||index>=size){
			throw new RuntimeException("容器索引值过大或过小");
		}
	}
	/**
	 * 验证容器非存在元素的索引值的范围
	 * @param index
	 */
	private void checkAddRange(int index){
		if(index<0||index>size){
			throw new RuntimeException("");
		}
	}
	/**
	 * 提供栈数据结构的弹栈操作
	 */
	public T pop(){
		return removeFirst();
	}
	
	/**
	 * 提供栈数据结构的入栈操作
	 */
	public void push(T t){
		addFirst(t);
	}
	/**
	 * 获取栈顶元素值
	 */
	public T Peek(){
		Node<T> temp=header;
		return (header==null)?null:header.element;
	}
	
	/**
	 * 提供队列数据结构的出队列
	 */
	public T poll(){
		return removeFirst();
	}
	/**
	 * 提供队列数据结构的入队列
	 */
	public void offer(T t){
		addLast(t);
	}
	
	/**
	 * 
	 * @param j 要删除的节点
	 * @return return 删除节点的值
	 */
	private T  removeNode(Node<T> j) {
		T temp =j.element;
		Node<T > pre = j.pre;
		Node<T>  next=j.next;
		if(pre==null){//判断要删除的节点是否为头结点。
			header=next;
		} else{
			pre.next=next;
			j.pre=null;
		}
		if(next==null){//判断要删除的节点是否未节点。
			tail=pre;
		} else{
			next.pre=pre;
			j.next=null;
		}
		j.element=null;
		return temp;
		
		
	}
	//双链表节点类
	private class Node<T>{
		T element;//元素值
		Node<T> pre;//前指针
		Node<T> next;//后指针
		//无参构造
		public Node(){
			
		}
		//含有所有成员变量的构造
	public Node (T element,Node<T> pre,Node<T> next){
			this.element=element;
			this.pre=pre;
			this.next=next;
	}
		
	}
}
