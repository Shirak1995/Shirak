beans {
    mySingletonBean(MySingletonBean) { bean ->
        bean.scope = 'singleton'
    }
    myPrototypeBean(MyPrototypeBean) { bean ->
        bean.scope = 'prototype'
    }
}

class MySingletonBean {
    MySingletonBean() {
        println 'MySingletonBean created'
    }
}

class MyPrototypeBean {
    MyPrototypeBean() {
        println 'MyPrototypeBean created'
    }
}