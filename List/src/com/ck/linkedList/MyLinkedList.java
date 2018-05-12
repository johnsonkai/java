package com.ck.linkedList;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 *<p>Title:MyArrayList</p>
 *<p>Description:�Զ���LinkedList</p>
 *<p>Company:cks</p>	
 * @author:ck
 * @date:2018-5-4
 */
public class MyLinkedList<T> {
	//MyLinkedList�����Ĵ�С
	private int size=0;
	//ָ����ʼͷ�ڵ�
	Node<T> header;
	//��ʼ��β�ڵ�
	Node<T> tail ;
	//�������
	public MyLinkedList(){
		
	}
	//MyLinkedList����������Ԫ�صķ�ʽ,ÿ�ν��ڵ���뵽�������һ���ڵ���档
	/**
	 * ������������ָ��Ԫ��ֵ��Ҳ����������������ָ��Ԫ��ֵ�Ľڵ�
	 * @param t
	 * @return 
	 */
	public  boolean add(T t){
		addLast(t);
		return true;
		
	}
	/**
	 * ������ָ������λ������ָ����Ԫ��ֵ
	 * @param index
	 * @param t
	 */
	public void add(int index,T t){
		checkAddRange(index);
		if(index==size){//�����������ӽڵ�
			add(t);
		}else{
			Node<T> next=getNode(index);
			Node<T> pre=next.pre;
			Node<T> addNode= new Node<T>(t, pre, next);	
			next.pre=addNode;
			if(pre==null){//��������һ��λ�������ڵ�,��������ͷ���
				header=pre;
			} else{//�������м������ڵ㡣
				pre.next=addNode;
			}
			
		}
	}
	/**
	 * ��Ԫ�ؽڵ������������е�һ���ڵ�,��һ�����ӵĽڵ����δ�ڵ㡣
	 * @param t
	 */
	public void addFirst(T t){
		Node<T> temp=header;
		Node<T> addNode= new Node<T>(t, null, temp);
		header=addNode;
		if(temp==null){//����Ϊ�գ��������ڵ�����Ϊδ�ڵ㡣
			tail=addNode;
		} else{//�����ǿգ��������ڵ���ӵ���һ���ڵ㡣
			temp.pre=addNode;
		}
		size++;
	}
	/**
	 * ��Ԫ�ؽڵ���ӵ����������һ���ڵ�
	 * @param t
	 */
	public void addLast(T t){
		Node<T> tempNode=tail;
		Node<T> addNode = new Node<T>(t,tail,null);
		tail=addNode;
		if(tempNode==null){//����û�нڵ�Ԫ�أ��������ڵ�Ԫ������Ϊͷ�ڵ�
			header=addNode;
		}  else{//�������нڵ�Ԫ�أ��������ڵ��������δ�ڵ���档
			tempNode.next=addNode;
		}
		size++;
	}
	/**
	 * ��������Ĵ�С
	 * @return
	 */
	public int  size(){
		return size;
	}
	/**
	 * ɾ��������ָ��Ԫ�ص�ֵ
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
	 * ����ָ����ָ��������ֵɾ��������Ӧ�Ľڵ㡣
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
	 * ɾ������ͷԪ�ؽڵ�
	 * @return
	 */
	public T removeFirst(){
		Node<T> temp=header;
		if(temp==null){
			throw new NoSuchElementException("����û�д��ڴ�Ԫ��");
		}
		return removeNode(temp);
	}
	
	/**
	 * ɾ������δԪ�ؽڵ�
	 * @return
	 */
	public T removeLast(){
		Node<T> temp=tail;
		if(temp==null){
			throw new NoSuchElementException("����û�д��ڴ�Ԫ��");
		}
		return removeNode(temp);
	}
	
	/**
	 * ������������ֵ��ȡ��ӦԪ��ֵ
	 * @param index
	 * @return
	 */
	public T get(int index){
		return getNode(index).element;
	}
	/**
	 * 
	 * ��ȡ�����е�һ���ڵ��Ԫ��ֵ
	 * @return
	 */
	public T getFirst(){
		Node<T> first=header;
		if(first==null){
			throw new NoSuchElementException("������������Ԫ��ֵ");
		}
		return header.element;
	}
	/**
	 * ��ȡ���������һ���ڵ��Ԫ��ֵ
	 */
	public T getLast(){
		Node<T> last=tail;
		if(last==null){
			throw new NoSuchElementException("������������Ԫ��ֵ");
		}
		return tail.element;
	}
	/**
	 * �ж������Ƿ�Ϊ��
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
	 * �������,�����еĽڵ����
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
	 * ��������ֵ��ȡ��Ӧ�ڵ�
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
	 * ��֤�����Ѿ�����Ԫ�ص�����ֵ�ķ�Χ
	 * @param index
	 */
	private void checkRange(int index){
		if(index<0||index>=size){
			throw new RuntimeException("��������ֵ������С");
		}
	}
	/**
	 * ��֤�����Ǵ���Ԫ�ص�����ֵ�ķ�Χ
	 * @param index
	 */
	private void checkAddRange(int index){
		if(index<0||index>size){
			throw new RuntimeException("");
		}
	}
	/**
	 * �ṩջ���ݽṹ�ĵ�ջ����
	 */
	public T pop(){
		return removeFirst();
	}
	
	/**
	 * �ṩջ���ݽṹ����ջ����
	 */
	public void push(T t){
		addFirst(t);
	}
	/**
	 * ��ȡջ��Ԫ��ֵ
	 */
	public T Peek(){
		Node<T> temp=header;
		return (header==null)?null:header.element;
	}
	
	/**
	 * �ṩ�������ݽṹ�ĳ�����
	 */
	public T poll(){
		return removeFirst();
	}
	/**
	 * �ṩ�������ݽṹ�������
	 */
	public void offer(T t){
		addLast(t);
	}
	
	/**
	 * 
	 * @param j Ҫɾ���Ľڵ�
	 * @return return ɾ���ڵ��ֵ
	 */
	private T  removeNode(Node<T> j) {
		T temp =j.element;
		Node<T > pre = j.pre;
		Node<T>  next=j.next;
		if(pre==null){//�ж�Ҫɾ���Ľڵ��Ƿ�Ϊͷ��㡣
			header=next;
		} else{
			pre.next=next;
			j.pre=null;
		}
		if(next==null){//�ж�Ҫɾ���Ľڵ��Ƿ�δ�ڵ㡣
			tail=pre;
		} else{
			next.pre=pre;
			j.next=null;
		}
		j.element=null;
		return temp;
		
		
	}
	//˫����ڵ���
	private class Node<T>{
		T element;//Ԫ��ֵ
		Node<T> pre;//ǰָ��
		Node<T> next;//��ָ��
		//�޲ι���
		public Node(){
			
		}
		//�������г�Ա�����Ĺ���
	public Node (T element,Node<T> pre,Node<T> next){
			this.element=element;
			this.pre=pre;
			this.next=next;
	}
		
	}
}
