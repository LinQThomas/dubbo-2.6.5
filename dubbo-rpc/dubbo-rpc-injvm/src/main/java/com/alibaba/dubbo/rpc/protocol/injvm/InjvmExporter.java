/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.protocol.injvm;

import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.AbstractExporter;

import java.util.Map;

/**
 * InjvmExporter
 */
class InjvmExporter<T> extends AbstractExporter<T> {

//    helloGroup/com.tianhe.lianxi.dubbo.api.HelloFacade:1.0.0
    private final String key;

//    "helloGroup/com.tianhe.lianxi.dubbo.api.HelloFacade:1.0.0" -> "interface com.tianhe.lianxi.dubbo.api.HelloFacade -> injvm://127.0.0.1/com.tianhe.lianxi.dubbo.api.HelloFacade?anyhost=true&application=dubbo-provider&bean.name=providers:dubbo:com.tianhe.lianxi.dubbo.api.HelloFacade:1.0.0:helloGroup&bind.ip=172.28.82.218&bind.port=20880&dubbo=2.0.2&executes=200&generic=false&group=helloGroup&interface=com.tianhe.lianxi.dubbo.api.HelloFacade&methods=sayHello&pid=2528&revision=1.0.0&side=provider&timestamp=1573207327686&version=1.0.0"
    private final Map<String, Exporter<?>> exporterMap;

    InjvmExporter(Invoker<T> invoker, String key, Map<String, Exporter<?>> exporterMap) {
        super(invoker);
        this.key = key;
        this.exporterMap = exporterMap;
        exporterMap.put(key, this);
    }

    @Override
    public void unexport() {
        super.unexport();
        exporterMap.remove(key);
    }

}
