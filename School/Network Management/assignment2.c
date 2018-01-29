#include <net-snmp/net-snmp-config.h>
#include <net-snmp/net-snmp-includes.h>
#include <string.h>
#include <time.h>
//FOR POSIX THREAD
#include <pthread.h>

pthread_t thread1, thread2;
int counter1, counter2;
netsnmp_session global_ss;

const char *oids[2];
oids[1] = "1.3.6.1.2.1.2.2.1.10" //data in
oids[2] = "1.3.6.1.2.1.2.2.1.16" //data out

void timer_thread() {
  counter1 = pthread_create( &thread1, NULL, monitor, &oids[1]);
  counter2 = pthread_create( &thread2, NULL, monitor, &oids[2]);

  pthread_join( thread1, NULL);
  pthread_join( thread2, NULL);

  exit(0);
}

void *monitor(char *OIDSpec){
  passedOID = OIDSpec
  timer_t start, timer_current;
  start = clock();
  timer_current = clock();

  while (timer_current =! 0){
    timer_current_elapsed = (start-timer_current)/ CLOCKS_PER_SEC;
    timer_current = clock();
    //every 60 seconds, complete action
    if(timer_current_elapsed % 60000000000 == 0){
      //write code to check oids
    }
    if(timer_current_elapsed % 600000000000 == 0){
      timer_current = 0
    }
    sleep(2)
    //sleep two seconds just for testing without blowing up the CPU,
    //since I do not know how fast this while loop might run
  }
}

void walk(char *OIDSpec){
  passedOID = OIDSpec

}

int main(int argc, char ** argv)
{
	netsnmp_session session, *ss;
	netsnmp_pdu *pdu;
	netsnmp_pdu *response;

	oid anOID[MAX_OID_LEN];
	size_t anOID_len;

	netsnmp_variable_list *vars;
	int status;
	int count=1;

  struct thread_arguments initial_args;

	//init the snmp library
	init_snmp("snmpdemoapp");

	//init a session
	snmp_sess_init( &session );
	session.peername = strdup("localhost");

	session.version = SNMP_VERSION_1;

	session.community = "public";
	session.community_len = strlen(session.community);

	//open session
	SOCK_STARTUP;
	ss = snmp_open(&session);

	if (!ss) {
		snmp_sess_perror("ack", &session);
		SOCK_CLEANUP;
		exit(1);
	}

	pdu = snmp_pdu_create(SNMP_MSG_GETNEXT);
	anOID_len = MAX_OID_LEN;

  timer_thread()

	if (!snmp_parse_oid(".1.3.6.1.2.1.4.24.4.1.4.0.0.0.0.0.0.0.0.0", anOID, &anOID_len)) {
		snmp_perror(".1.3.6.1.2.1.4.24.4.1.4.0.0.0.0.0.0.0.0.0");
	//	.1.3.6.1.2.1.4.35.1.4.2.1.4 is the OID
	//	192.168.1.1 is the key
	//	together they form the full setup
	//if (!snmp_parse_oid(".1.3.6.1.2.1.1.9.1.3", anOID, &anOID_len)) {
	//	snmp_perror(".1.3.6.1.2.1.1.9.1.3");
		SOCK_CLEANUP;
		exit(1);
	}

	snmp_add_null_var(pdu, anOID, anOID_len);

	status = snmp_synch_response(ss, pdu, &response);

	if (status == STAT_SUCCESS && response->errstat == SNMP_ERR_NOERROR) {

		for(vars = response->variables; vars; vars = vars->next_variable)
			print_variable(vars->name, vars->name_length, vars);

		/* manipuate the information ourselves */
		for(vars = response->variables; vars; vars = vars->next_variable) {
			if(vars->type == ASN_IPADDRESS)
			{
				uint32_t ipaddr;
				vars->name_length = 4;
				vars->name = vars->name_loc;
				memcpy(&ipaddr, vars->val.string, sizeof(ipaddr));
				vars->name[0] = (ipaddr >> 24) & 0xff;
				vars->name[1] = (ipaddr >> 16) & 0xff;
				vars->name[2] = (ipaddr >> 8 ) & 0xff;
				vars->name[3] = (ipaddr >> 0 ) & 0xff;

				printf("The ipaddress is %d.%d.%d.%d\n", vars->name[3], vars->name[2], vars->name[1], vars->name[0]);

			}else if(vars->type == ASN_INTEGER)
			{
				printf("This is an int %d\n", vars->val.integer);
			}else if (vars->type == ASN_OCTET_STR) {
				char *sp = (char *)malloc(1 + vars->val_len);
				memcpy(sp, vars->val.string, vars->val_len);
				sp[vars->val_len] = '\0';
				printf("value #%d is a string: %s\n", count++, sp);
				free(sp);
				}
			else
				printf("value #%d is NOT a string! Ack!\n", count++);
			}
	} else {



	if (status == STAT_SUCCESS)
		fprintf(stderr, "Error in packet\nReason: %s\n",
		snmp_errstring(response->errstat));
	else if (status == STAT_TIMEOUT)
		fprintf(stderr, "Timeout: No response from %s.\n", session.peername);
	else
		snmp_sess_perror("snmpdemoapp", ss);
	}

	if (response)
		snmp_free_pdu(response);
		snmp_close(ss);
		SOCK_CLEANUP;
	return (0);
} /* main() */
