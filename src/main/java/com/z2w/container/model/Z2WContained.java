package com.z2w.container.model;

/**
 * 定义一个contained接口，实现该接口的对象可以被放置在容器中
 *
 */
public interface Z2WContained {

	public abstract Z2WContainer getContainer();

	public abstract void setContainer(Z2WContainer container);
}
