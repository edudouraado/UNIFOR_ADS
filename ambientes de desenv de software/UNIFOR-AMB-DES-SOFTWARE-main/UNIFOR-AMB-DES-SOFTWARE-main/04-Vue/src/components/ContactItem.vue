<template>
    <div class="contact">
        <h3>{{ name }}</h3>
        <p>Email: {{ email }}</p>
        <p>Phone: {{ phone }}</p>
    </div>
</template>

<script>
export default {
    name: 'ContactItem',
    props: {
        name: String,
        email: String,
        phone: String,
    },
};
</script>

<style scoped>
.contact {
    background: #61dafb;
    margin: 10px;
    padding: 15px;
    border-radius: 5px;
    width: 300px;
    text-align: left;
}
</style>