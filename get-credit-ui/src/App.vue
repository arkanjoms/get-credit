<template>
    <div id="app">
        <Navbar/>
        <main>
            <router-view/>
        </main>
    </div>
</template>

<script>
    import Navbar from "./views/Navbar";

    export default {
        components: {
            Navbar
        }
    }
</script>

<style>
    main {
        padding: 10px;
    }
</style>
