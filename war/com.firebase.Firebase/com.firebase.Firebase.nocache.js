function com_firebase_Firebase(){var O='',wb='" for "gwt:onLoadErrorFn"',ub='" for "gwt:onPropertyErrorFn"',hb='"><\/script>',Y='#',Gb='.cache.html',$='/',kb='//',Fb=':',ob='::',Ib='<script defer="defer">com_firebase_Firebase.onInjectionDone(\'com.firebase.Firebase\')<\/script>',gb='<script id="',rb='=',Z='?',tb='Bad handler "',Eb='CC18EB17B1700EC83C80E3A49A4B5E0B',Hb='DOMContentLoaded',ib='SCRIPT',fb='__gwt_marker_com.firebase.Firebase',jb='base',bb='baseUrl',S='begin',R='bootstrap',ab='clear.cache.gif',P='com.firebase.Firebase',db='com.firebase.Firebase.nocache.js',nb='com.firebase.Firebase::',qb='content',X='end',T='gwt.codesvr=',U='gwt.hosted=',V='gwt.hybrid',vb='gwt:onLoadErrorFn',sb='gwt:onPropertyErrorFn',pb='gwt:property',Cb='hosted.html?com_firebase_Firebase',xb='iframe',_='img',yb="javascript:''",Bb='loadExternalRefs',lb='meta',Ab='moduleRequested',W='moduleStartup',mb='name',zb='position:absolute;width:0;height:0;border:none',cb='script',Db='selectingPermutation',Q='startup',eb='undefined';var m=window,n=document,o=m.__gwtStatsEvent?function(a){return m.__gwtStatsEvent(a)}:null,p=m.__gwtStatsSessionId?m.__gwtStatsSessionId:null,q,r,s,t=O,u={},v=[],w=[],x=[],y=0,z,A;o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:R,millis:(new Date).getTime(),type:S});if(!m.__gwt_stylesLoaded){m.__gwt_stylesLoaded={}}if(!m.__gwt_scriptsLoaded){m.__gwt_scriptsLoaded={}}function B(){var b=false;try{var c=m.location.search;return (c.indexOf(T)!=-1||(c.indexOf(U)!=-1||m.external&&m.external.gwtOnLoad))&&c.indexOf(V)==-1}catch(a){}B=function(){return b};return b}
function C(){if(q&&r){var b=n.getElementById(P);var c=b.contentWindow;if(B()){c.__gwt_getProperty=function(a){return G(a)}}com_firebase_Firebase=null;c.gwtOnLoad(z,P,t,y);o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:W,millis:(new Date).getTime(),type:X})}}
function D(){function e(a){var b=a.lastIndexOf(Y);if(b==-1){b=a.length}var c=a.indexOf(Z);if(c==-1){c=a.length}var d=a.lastIndexOf($,Math.min(c,b));return d>=0?a.substring(0,d+1):O}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=n.createElement(_);b.src=a+ab;a=e(b.src)}return a}
function g(){var a=F(bb);if(a!=null){return a}return O}
function h(){var a=n.getElementsByTagName(cb);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(db)!=-1){return e(a[b].src)}}return O}
function i(){var a;if(typeof isBodyLoaded==eb||!isBodyLoaded()){var b=fb;var c;n.write(gb+b+hb);c=n.getElementById(b);a=c&&c.previousSibling;while(a&&a.tagName!=ib){a=a.previousSibling}if(c){c.parentNode.removeChild(c)}if(a&&a.src){return e(a.src)}}return O}
function j(){var a=n.getElementsByTagName(jb);if(a.length>0){return a[a.length-1].href}return O}
function k(){var a=n.location;return a.href==a.protocol+kb+a.host+a.pathname+a.search+a.hash}
var l=g();if(l==O){l=h()}if(l==O){l=i()}if(l==O){l=j()}if(l==O&&k()){l=e(n.location.href)}l=f(l);t=l;return l}
function E(){var b=document.getElementsByTagName(lb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(mb),g;if(f){f=f.replace(nb,O);if(f.indexOf(ob)>=0){continue}if(f==pb){g=e.getAttribute(qb);if(g){var h,i=g.indexOf(rb);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=O}u[f]=h}}else if(f==sb){g=e.getAttribute(qb);if(g){try{A=eval(g)}catch(a){alert(tb+g+ub)}}}else if(f==vb){g=e.getAttribute(qb);if(g){try{z=eval(g)}catch(a){alert(tb+g+wb)}}}}}}
function F(a){var b=u[a];return b==null?null:b}
function G(a){var b=w[a](),c=v[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(A){A(a,d,b)}throw null}
var H;function I(){if(!H){H=true;var a=n.createElement(xb);a.src=yb;a.id=P;a.style.cssText=zb;a.tabIndex=-1;n.body.appendChild(a);o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:W,millis:(new Date).getTime(),type:Ab});a.contentWindow.location.replace(t+K)}}
com_firebase_Firebase.onScriptLoad=function(){if(H){r=true;C()}};com_firebase_Firebase.onInjectionDone=function(){q=true;o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:Bb,millis:(new Date).getTime(),type:X});C()};E();D();var J;var K;if(B()){if(m.external&&(m.external.initModule&&m.external.initModule(P))){m.location.reload();return}K=Cb;J=O}o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:R,millis:(new Date).getTime(),type:Db});if(!B()){try{J=Eb;var L=J.indexOf(Fb);if(L!=-1){y=Number(J.substring(L+1));J=J.substring(0,L)}K=J+Gb}catch(a){return}}var M;function N(){if(!s){s=true;C();if(n.removeEventListener){n.removeEventListener(Hb,N,false)}if(M){clearInterval(M)}}}
if(n.addEventListener){n.addEventListener(Hb,function(){I();N()},false)}var M=setInterval(function(){if(/loaded|complete/.test(n.readyState)){I();N()}},50);o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:R,millis:(new Date).getTime(),type:X});o&&o({moduleName:P,sessionId:p,subSystem:Q,evtGroup:Bb,millis:(new Date).getTime(),type:S});n.write(Ib)}
com_firebase_Firebase();