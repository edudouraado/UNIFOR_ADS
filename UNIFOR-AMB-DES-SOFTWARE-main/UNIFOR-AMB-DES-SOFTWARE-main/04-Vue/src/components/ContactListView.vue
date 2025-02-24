<template>
    <div>
        <h2>Contact List</h2>
        <ContactItem v-for="contact in contacts" :key="contact.id" :name="contact.name" :email="contact.email"
            :phone="contact.phone" />
    </div>
</template>

<script>
import ContactItem from './ContactItem.vue';

export default {
    name: 'ContactListView',
    components: {
        ContactItem,
    },
    data() {
        return {
            contacts: [
                { id: 1, name: 'John Doe', email: 'john@example.com', phone: '123-456-7890' },
                { id: 2, name: 'Jane Smith', email: 'jane@example.com', phone: '098-765-4321' },
                { id: 3, name: 'Bob Johnson', email: 'bob@example.com', phone: '111-222-3333' },
            ],
        };
    },
};
</script>

<style scoped></style>