struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2)
{
    int carry = 0;

    struct ListNode *head = NULL;
    struct ListNode *tail = NULL;

    while(l1 != NULL || l2 != NULL || carry)
    {
        int x = 0;
        int y = 0;

        if(l1 != NULL)
        {
            x = l1->val;
            l1 = l1->next;
        }

        if(l2 != NULL)
        {
            y = l2->val;
            l2 = l2->next;
        }

        int sum = x + y + carry;

        carry = sum / 10;

        struct ListNode *newNode =
        (struct ListNode*)malloc(sizeof(struct ListNode));

        newNode->val = sum % 10;
        newNode->next = NULL;

        if(head == NULL)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail->next = newNode;
            tail = newNode;
        }
    }

    return head;
}