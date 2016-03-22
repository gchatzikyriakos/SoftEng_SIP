  _________                       ____  __.____  __.             __                 
 /   _____/ ____ ___  ______.__. |    |/ _|    |/ _|____   _____/  |_  ____   ____  
 \_____  \_/ __ \\  \/  <   |  | |      < |      < /  _ \ /    \   __\/  _ \ / ___\ 
 /        \  ___/ >    < \___  | |    |  \|    |  (  <_> )   |  \  | (  <_> ) /_/  >
/_______  /\___  >__/\_ \/ ____| |____|__ \____|__ \____/|___|  /__|  \____/\___  / 
        \/     \/      \/\/              \/       \/          \/           /_____/  


                :::::: PROJECT PACKAGES ::::::

1.  SipCommunicator
    
    net.java.sip.communicator --
                               |---extensions ---> BlockUI
                               |                   ConnectToDB
                               |                   ForwardUI
                               |                   InvalidCredUI
                               |                   RegistrationToDB
                               |
                               |---gui-----------> MenuBar
                               |
                               |---sip-----------> SipManager
                               |
                               _
                               
2. SipProxy

    gov.nist.sip.proxy ---------
                               |-----------------> Proxy
                               |
                               |---extensions----> Blocker
                               |                   ConnectToDB
                               |                   Forwarder
                               |                   ProxyDecorator
                               |                   SipListenerDec
                               _
                               